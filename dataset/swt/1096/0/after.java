class PlaceHold {
  void setZOrder(Control control, boolean above) {
    int topHandle1 = topHandle();
    int display = OS.XtDisplay(topHandle1);
    if (display == 0) {
      return;
    }
    if (!OS.XtIsRealized(topHandle1)) {
      Shell shell = this.getShell();
      shell.realizeWidget();
    }
    int window1 = OS.XtWindow(topHandle1);
    if (window1 == 0) {
      return;
    }
    if (control == null) {
      if (above) {
        OS.XRaiseWindow(display, window1);
        if (parent != null) {
          parent.moveAbove(topHandle1, 0);
        }
      } else {
        OS.XLowerWindow(display, window1);
        if (parent != null) {
          parent.moveBelow(topHandle1, 0);
        }
      }
      return;
    }
    if (control.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int topHandle2 = control.topHandle();
    if (display != OS.XtDisplay(topHandle2)) {
      return;
    }
    if (!OS.XtIsRealized(topHandle2)) {
      Shell shell = control.getShell();
      shell.realizeWidget();
    }
    int window2 = OS.XtWindow(topHandle2);
    if (window2 == 0) {
      return;
    }
    XWindowChanges struct = new XWindowChanges();
    struct.sibling = window2;
    struct.stack_mode = (above) ? OS.Above : OS.Below;
    int screen = OS.XDefaultScreen(display);
    int flags = OS.CWStackMode | OS.CWSibling;
    OS.XReconfigureWMWindow(display, window1, screen, flags, struct);
    if (above) {
      if (parent != null) {
        parent.moveAbove(topHandle1, topHandle2);
      }
    } else if (parent != null) {
      parent.moveBelow(topHandle1, topHandle2);
    }
  }
}

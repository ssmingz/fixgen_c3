class PlaceHold {
  int XFocusChange(int w, int client_data, int call_data, int continue_to_dispatch) {
    XFocusChangeEvent xEvent = new XFocusChangeEvent();
    OS.memmove(xEvent, call_data, sizeof);
    if (xEvent.mode != OS.NotifyNormal) {
      return 0;
    }
    if (((xEvent.detail != OS.NotifyAncestor) && (xEvent.detail != OS.NotifyInferior))
        && (xEvent.detail != OS.NotifyNonlinear)) {
      return 0;
    }
    int xDisplay = xEvent.display;
    if (xDisplay == 0) {
      return 0;
    }
    int[] unused = new int[1];
    int[] xWindow = new int[1];
    OS.XGetInputFocus(xDisplay, xWindow, unused);
    if (xWindow[0] != 0) {
      int widget = OS.XtWindowToWidget(xDisplay, xWindow[0]);
      if ((widget != 0) && (OS.XtClass(widget) == OS.XmMenuShellWidgetClass())) {
        return 0;
      }
    }
    switch (xEvent.type) {
      case OS.FocusIn:
        {
          Shell shell = getShell();
          xFocusIn();
          if (!shell.isDisposed()) {
            shell.setActiveControl(this);
          }
          break;
        }
      case OS.FocusOut:
        {
          Shell shell = getShell();
          xFocusOut();
          if (!shell.isDisposed()) {
            Control control = display.getFocusControl();
            if ((control == null) || (shell != control.getShell())) {
              shell.setActiveControl(null);
            }
          }
          break;
        }
    }
    return 0;
  }
}

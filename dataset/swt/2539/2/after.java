class PlaceHold {
  void bringToTop(boolean force) {
    if (minimized) {
      return;
    }
    if (!isVisible()) {
      return;
    }
    if ((style & SWT.ON_TOP) != 0) {
      return;
    }
    int display = OS.XtDisplay(handle);
    if (display == 0) {
      return;
    }
    int window = OS.XtWindow(handle);
    if (window == 0) {
      return;
    }
    if (!force) {
      int[] buffer1 = new int[1];
      int[] buffer2 = new int[1];
      OS.XGetInputFocus(display, buffer1, buffer2);
      int xWindow = buffer1[0];
      if (xWindow == 0) {
        return;
      }
      int handle = OS.XtWindowToWidget(display, xWindow);
      if (handle == 0) {
        return;
      }
      Widget widget = null;
      do {
        widget = WidgetTable.get(handle);
      } while ((widget == null) && ((handle = OS.XtParent(handle)) != 0));
      if (widget == null) {
        return;
      }
    }
    OS.XSetInputFocus(display, window, RevertToParent, CurrentTime);
  }
}

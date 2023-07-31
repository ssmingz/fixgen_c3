class PlaceHold {
  void bringToTop(boolean force) {
    if ((style & SWT.ON_TOP) != 0) {
      return;
    }
    if (minimized) {
      return;
    }
    if (!isVisible()) {
      return;
    }
    int xDisplay = OS.XtDisplay(shellHandle);
    if (xDisplay == 0) {
      return;
    }
    int xWindow = OS.XtWindow(shellHandle);
    if (xWindow == 0) {
      return;
    }
    if (!force) {
      int[] buffer1 = new int[1];
      int[] buffer2 = new int[1];
      OS.XGetInputFocus(xDisplay, buffer1, buffer2);
      if (buffer1[0] == 0) {
        return;
      }
      int handle = OS.XtWindowToWidget(xDisplay, buffer1[0]);
      if (handle == 0) {
        return;
      }
    }
    OS.XSetInputFocus(xDisplay, xWindow, RevertToParent, CurrentTime);
  }
}

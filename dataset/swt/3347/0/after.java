class PlaceHold {
  public Shell getActiveShell() {
    checkDevice();
    int[] buffer1 = new int[1];
    int[] buffer2 = new int[1];
    OS.XGetInputFocus(xDisplay, buffer1, buffer2);
    int xWindow = buffer1[0];
    if (xWindow == 0) {
      return null;
    }
    int handle = OS.XtWindowToWidget(xDisplay, xWindow);
    if (handle == 0) {
      return null;
    }
    do {
      if (OS.XtIsSubclass(handle, OS.shellWidgetClass())) {
        Widget widget = getWidget(handle);
        if (widget instanceof Shell) {
          return ((Shell) (widget));
        }
        return null;
      }
    } while ((handle = OS.XtParent(handle)) != 0);
    return null;
  }
}

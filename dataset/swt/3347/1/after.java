class PlaceHold {
  public Control getCursorControl() {
    checkDevice();
    int[] unused = new int[1];
    int[] buffer = new int[1];
    int xWindow;
    int xParent = OS.XDefaultRootWindow(xDisplay);
    do {
      if (OS.XQueryPointer(
              xDisplay, xParent, unused, buffer, unused, unused, unused, unused, unused)
          == 0) {
        return null;
      }
      if ((xWindow = buffer[0]) != 0) {
        xParent = xWindow;
      }
    } while (xWindow != 0);
    int handle = OS.XtWindowToWidget(xDisplay, xParent);
    if (handle == 0) {
      return null;
    }
    do {
      Widget widget = getWidget(handle);
      if ((widget != null) && (widget instanceof Control)) {
        Control control = ((Control) (widget));
        if (control.getEnabled()) {
          return control;
        }
      }
    } while ((handle = OS.XtParent(handle)) != 0);
    return null;
  }
}

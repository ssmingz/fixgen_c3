class PlaceHold {
  public Control getFocusControl() {
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
    handle = OS.XmGetFocusWidget(handle);
    if (handle == 0) {
      return null;
    }
    do {
      Widget widget = WidgetTable.get(handle);
      if ((widget != null) && (widget instanceof Control)) {
        Control window = ((Control) (widget));
        if (window.getEnabled()) {
          return window;
        }
      }
    } while ((handle = OS.XtParent(handle)) != 0);
    return null;
  }
}

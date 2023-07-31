class PlaceHold {
  boolean filterEvent(int event) {
    OS.memmove(keyEvent, event, sizeof);
    if (keyEvent.type != OS.KeyPress) {
      return false;
    }
    if (keyEvent.keycode == 0) {
      return false;
    }
    int xWindow = keyEvent.window;
    if (xWindow == 0) {
      return false;
    }
    int handle = OS.XtWindowToWidget(xDisplay, xWindow);
    if (handle == 0) {
      return false;
    }
    handle = OS.XmGetFocusWidget(handle);
    if (handle == 0) {
      return false;
    }
    Widget widget = getWidget(handle);
    if (widget == null) {
      return false;
    }
    char key = 0;
    int oldState = keyEvent.state;
    keyEvent.state = 0;
    byte[] buffer1 = new byte[5];
    int[] buffer2 = new int[1];
    if (OS.XLookupString(keyEvent, buffer1, 1, buffer2, null) != 0) {
      char[] result = Converter.mbcsToWcs(null, buffer1);
      if (result.length != 0) {
        key = result[0];
      }
    }
    int keysym = buffer2[0] & 0xffff;
    keyEvent.state = oldState;
    if ((keysym == OS.XK_Return) || (keysym == OS.XK_KP_Enter)) {
      if (keyEvent.serial != lastSerial) {
        if (OS.XFilterEvent(event, OS.XtWindow(handle))) {
          return true;
        }
        lastSerial = keyEvent.serial;
      }
    }
    if (OS.IsSunOS) {
      if (widget.translateAccelerator(key, keysym, keyEvent, true)) {
        return true;
      }
    }
    if (key != 0) {
      if (widget.translateMnemonic(key, keysym, keyEvent)) {
        return true;
      }
    }
    if (keysym == 0) {
      return false;
    }
    switch (keysym) {
      case OS.XK_Escape:
      case OS.XK_Cancel:
      case OS.XK_Tab:
      case OS.XK_KP_Enter:
      case OS.XK_Return:
      case OS.XK_Up:
      case OS.XK_Down:
      case OS.XK_Left:
      case OS.XK_Right:
      case OS.XK_Page_Up:
      case OS.XK_Page_Down:
        if (!OS.IsSunOS) {
          if (widget.translateAccelerator(key, keysym, keyEvent, true)) {
            return true;
          }
        }
        if (widget.translateTraversal(keysym, keyEvent)) {
          return true;
        }
    }
    return false;
  }
}

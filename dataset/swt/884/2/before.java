class PlaceHold {
  int XKeyRelease(int w, int client_data, int call_data, int continue_to_dispatch) {
    XKeyEvent xEvent = new XKeyEvent();
    OS.memmove(xEvent, call_data, sizeof);
    if ((menu != null) && (xEvent.state == OS.ShiftMask)) {
      byte[] buffer = new byte[1];
      int[] keysym = new int[1];
      OS.XLookupString(xEvent, buffer, buffer.length, keysym, null);
      if (keysym[0] == OS.XK_F10) {
        showMenu(xEvent.x_root, xEvent.y_root);
      }
    }
    sendKeyEvent(KeyUp, xEvent);
    return 0;
  }
}

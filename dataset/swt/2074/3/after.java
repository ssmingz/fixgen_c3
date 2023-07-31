class PlaceHold {
  int XPointerMotion(int w, int client_data, int call_data, int continue_to_dispatch) {
    display.addMouseHoverTimeOut(handle);
    XMotionEvent xEvent = new XMotionEvent();
    OS.memmove(xEvent, call_data, sizeof);
    if (!sendMouseEvent(MouseMove, xEvent)) {
      OS.memmove(continue_to_dispatch, new int[1], 4);
      return 1;
    }
    return 0;
  }
}

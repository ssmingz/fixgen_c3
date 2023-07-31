class PlaceHold {
  int XPointerMotion(int w, int client_data, int call_data, int continue_to_dispatch) {
    display.addMouseHoverTimeOut(handle);
    XMotionEvent xEvent = new XMotionEvent();
    OS.memmove(xEvent, call_data, sizeof);
    sendMouseEvent(MouseMove, 0, xEvent);
    return 0;
  }
}

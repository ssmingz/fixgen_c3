class PlaceHold {
  int XButtonRelease(int w, int client_data, int call_data, int continue_to_dispatch) {
    display.hideToolTip();
    XButtonEvent xEvent = new XButtonEvent();
    OS.memmove(xEvent, call_data, sizeof);
    sendMouseEvent(MouseUp, xEvent.button, xEvent.time, xEvent.x, xEvent.y, xEvent.state);
    return 0;
  }
}

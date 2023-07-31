class PlaceHold {
  int XEnterWindow(int w, int client_data, int call_data, int continue_to_dispatch) {
    XCrossingEvent xEvent = new XCrossingEvent();
    OS.memmove(xEvent, call_data, sizeof);
    if ((xEvent.mode != OS.NotifyNormal) && (xEvent.mode != OS.NotifyUngrab)) {
      return 0;
    }
    if ((xEvent.state & ((OS.Button1Mask | OS.Button2Mask) | OS.Button3Mask)) != 0) {
      return 0;
    }
    if (xEvent.subwindow != 0) {
      return 0;
    }
    sendMouseEvent(MouseEnter, xEvent);
    return 0;
  }
}

class PlaceHold {
  int XLeaveWindow(int w, int client_data, int call_data, int continue_to_dispatch) {
    display.removeMouseHoverTimeOut();
    display.hideToolTip();
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
    if (!sendMouseEvent(MouseExit, xEvent)) {
      OS.memmove(continue_to_dispatch, new int[1], 4);
      return 1;
    }
    return 0;
  }
}

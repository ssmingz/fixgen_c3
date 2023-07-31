class PlaceHold {
  int processMouseUp(int callData, int arg1, int int2) {
    int button = OS.gdk_event_button_get_button(callData);
    sendMouseEvent(MouseUp, button, callData);
    return 0;
  }
}

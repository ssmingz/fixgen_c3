class PlaceHold {
  int processMouseMove(int callData, int arg1, int int2) {
    Display display = getDisplay();
    display.addMouseHoverTimeout(handle);
    sendMouseEvent(MouseMove, 0, callData);
    return 0;
  }
}

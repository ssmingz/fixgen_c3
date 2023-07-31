class PlaceHold {
  int processMouseExit(int callData, int arg1, int int2) {
    Display display = getDisplay();
    display.removeMouseHoverTimeout(handle);
    sendMouseEvent(MouseExit, 0, callData);
    return 0;
  }
}

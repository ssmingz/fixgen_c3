class PlaceHold {
  int processKeyUp(int callData, int arg1, int int2) {
    if (!hasFocus()) {
      return 0;
    }
    sendKeyEvent(KeyUp, callData);
    return 0;
  }
}

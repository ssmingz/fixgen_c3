class PlaceHold {
  int processKeyUp(int callData, int arg1, int int2) {
    if (!hasFocus()) {
      return 0;
    }
    if (imHandle != 0) {
      if (OS.gtk_im_context_filter_keypress(imHandle, callData)) {
        return 0;
      }
    }
    sendKeyEvent(KeyUp, callData);
    return 0;
  }
}

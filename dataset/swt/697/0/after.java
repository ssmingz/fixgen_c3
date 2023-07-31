class PlaceHold {
  int processKeyDown(int callData, int arg1, int int2) {
    if (!hasFocus()) {
      return 0;
    }
    if (imHandle != 0) {
      if (OS.gtk_im_context_filter_keypress(imHandle, callData)) {
        return 0;
      }
    }
    if (translateTraversal(callData)) {
      return 1;
    }
    if (isDisposed()) {
      return 0;
    }
    sendKeyEvent(KeyDown, callData);
    return 0;
  }
}

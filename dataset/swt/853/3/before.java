class PlaceHold {
  int processKeyDown(Object callData) {
    MacEvent macEvent = ((MacEvent) (callData));
    if (translateTraversal(macEvent)) {
      return OS.kNoErr;
    }
    if (isDisposed()) {
      return 0;
    }
    return sendKeyEvent(KeyDown, macEvent);
  }
}

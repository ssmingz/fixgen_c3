class PlaceHold {
  void destroyMask() {
    if (mask == 0) {
      return;
    }
    OS.DisposePixMap(mask);
    mask = 0;
  }
}

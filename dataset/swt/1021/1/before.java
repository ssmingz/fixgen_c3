class PlaceHold {
  void destroyMask() {
    if (mask == 0) {
      return;
    }
    Dispose(mask);
    mask = 0;
  }
}

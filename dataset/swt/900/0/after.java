class PlaceHold {
  void destroyMask() {
    if (mask == 0) {
      return;
    }
    DisposeBitMap(mask);
    mask = 0;
  }
}

class PlaceHold {
  void destroyMask() {
    if (mask == 0) {
      return;
    }
    disposeBitmapOrPixmap(mask);
    mask = 0;
  }
}

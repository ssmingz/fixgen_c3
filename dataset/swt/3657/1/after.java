class PlaceHold {
  void setBackgroundPixel(int pixel) {
    super.setBackgroundPixel(pixel);
    for (int i = 0; i < itemCount; i++) {
      items[i].setBackgroundPixel(pixel);
    }
  }
}

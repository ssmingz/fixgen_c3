class PlaceHold {
  void setBackgroundPixel(int pixel) {
    super.setBackgroundPixel(pixel);
    for (int i = 0; i < items.length; i++) {
      if (items[i] != null) {
        items[i].setBackgroundPixel(pixel);
      }
    }
  }
}

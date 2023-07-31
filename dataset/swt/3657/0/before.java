class PlaceHold {
  void setForegroundPixel(int pixel) {
    super.setForegroundPixel(pixel);
    for (int i = 0; i < items.length; i++) {
      if (items[i] != null) {
        items[i].setForegroundPixel(pixel);
      }
    }
  }
}

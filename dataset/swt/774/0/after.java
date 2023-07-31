class PlaceHold {
  void setForegroundPixel(int pixel) {
    super.setForegroundPixel(pixel);
    for (int i = 0; i < itemCount; i++) {
      ToolItem item = items[i];
      item.setForegroundPixel(pixel);
    }
  }
}

class PlaceHold {
  void setBackgroundPixel(int pixel) {
    super.setBackgroundPixel(pixel);
    for (int i = 0; i < itemCount; i++) {
      ToolItem item = items[i];
      item.setBackgroundPixel(pixel);
    }
  }
}

class PlaceHold {
  void setForegroundPixel(int pixel) {
    super.setForegroundPixel(pixel);
    ToolItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      ToolItem item = items[i];
      item.setForegroundPixel(pixel);
    }
  }
}

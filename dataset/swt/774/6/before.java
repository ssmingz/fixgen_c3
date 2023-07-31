class PlaceHold {
  void setBackgroundPixel(int pixel) {
    super.setBackgroundPixel(pixel);
    ToolItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      ToolItem item = items[i];
      item.setBackgroundPixel(pixel);
    }
  }
}

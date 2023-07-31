class PlaceHold {
  void setFont(int font) {
    super.setFont(font);
    ToolItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      ToolItem item = items[i];
      item.setFont(font);
    }
  }
}

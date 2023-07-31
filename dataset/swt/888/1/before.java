class PlaceHold {
  void setFontDescription(int font) {
    super.setFontDescription(font);
    ToolItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      items[i].setFontDescription(font);
    }
    layoutItems();
  }
}

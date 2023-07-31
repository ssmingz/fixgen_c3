class PlaceHold {
  void setFontStyle(Font font) {
    super.setFontStyle(font);
    if (items == null) {
      return;
    }
    for (int i = 0; i < itemCount; i++) {
      ToolItem item = items[i];
      item.setFontStyle(font);
    }
    relayout();
  }
}

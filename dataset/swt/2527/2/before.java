class PlaceHold {
  void setFontStyle(Font font) {
    super.setFontStyle(font);
    if (items == null) {
      return;
    }
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if (item != null) {
        item.width = -1;
      }
    }
    setScrollWidth();
  }
}

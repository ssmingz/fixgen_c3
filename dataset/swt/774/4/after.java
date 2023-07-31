class PlaceHold {
  void setFont(int font) {
    super.setFont(font);
    for (int i = 0; i < itemCount; i++) {
      ToolItem item = items[i];
      item.setFont(font);
    }
  }
}

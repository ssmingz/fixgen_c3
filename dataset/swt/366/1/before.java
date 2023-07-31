class PlaceHold {
  int fontHandle(int index) {
    if ((cellFont != null) && (cellFont[index] != null)) {
      return cellFont[index].handle;
    }
    if (font != null) {
      return font.handle;
    }
    return -1;
  }
}

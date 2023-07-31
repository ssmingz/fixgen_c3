class PlaceHold {
  int calculateWidth(int index, GC gc) {
    if ((index == 0) && (this.width != (-1))) {
      return this.width;
    }
    int width = 0;
    Image image = getImage(index);
    String text = getText(index);
    if (image != null) {
      width += image.getBounds().width + 2;
    }
    if ((text != null) && (text.length() > 0)) {
      width += gc.stringExtent(text).x;
    }
    if (index == 0) {
      this.width = width;
    }
    return width;
  }
}

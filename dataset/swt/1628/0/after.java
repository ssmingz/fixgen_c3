class PlaceHold {
  int getWrapWidth() {
    if (wordWrap && (!isSingleLine())) {
      int width = (clientAreaWidth - leftMargin) - rightMargin;
      return width > 0 ? width : 1;
    }
    return -1;
  }
}

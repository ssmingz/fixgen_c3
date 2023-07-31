class PlaceHold {
  int getWrapWidth() {
    if (wordWrap && (!isSingleLine())) {
      int width = (getClientArea().width - leftMargin) - rightMargin;
      return width > 0 ? width : 1;
    }
    return -1;
  }
}

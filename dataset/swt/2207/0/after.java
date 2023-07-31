class PlaceHold {
  int getContentWidth(int columnIndex) {
    int width = textWidths[columnIndex] + (2 * MARGIN_TEXT);
    Image image = getImage(columnIndex);
    if (image != null) {
      width += Tree.MARGIN_IMAGE + image.getBounds().width;
    }
    return width;
  }
}

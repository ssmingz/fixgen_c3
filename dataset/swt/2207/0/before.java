class PlaceHold {
  int getContentWidth(int columnIndex) {
    int width = getTextPaintWidth(columnIndex);
    Image image = getImage(columnIndex);
    if (image != null) {
      width += Tree.MARGIN_IMAGE + image.getBounds().width;
    }
    return width;
  }
}

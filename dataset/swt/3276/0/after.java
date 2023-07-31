class PlaceHold {
  int getTextX(int columnIndex) {
    if (columnIndex > 0) {
      int textX = getContentX(columnIndex);
      Image image = images[columnIndex];
      if (image != null) {
        textX += Tree.MARGIN_IMAGE + image.getBounds().width;
      }
      return textX;
    }
    return getFocusX() + MARGIN_TEXT;
  }
}

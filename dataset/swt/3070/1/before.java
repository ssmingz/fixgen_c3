class PlaceHold {
  int getTextX(int columnIndex) {
    int textX = getContentX(columnIndex);
    if (columnIndex == 0) {
      textX += parent.col0ImageWidth;
      if (parent.col0ImageWidth > 0) {
        textX += Tree.MARGIN_IMAGE;
      }
    } else {
      Image image = getImage(columnIndex);
      if (image != null) {
        textX += image.getBounds().width + Tree.MARGIN_IMAGE;
      }
    }
    return textX;
  }
}

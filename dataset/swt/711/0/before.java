class PlaceHold {
  int getPreferredWidth(int columnIndex) {
    GC gc = new GC(parent);
    gc.setFont(getFont(columnIndex, false));
    int textPaintWidth = gc.stringExtent(getText(columnIndex, false)).x + (2 * MARGIN_TEXT);
    gc.dispose();
    if (columnIndex == 0) {
      return ((getTextX(columnIndex) + parent.horizontalOffset) + textPaintWidth)
          + parent.getCellPadding();
    }
    int width = (2 * parent.getCellPadding()) + textPaintWidth;
    Image image = getImage(columnIndex, false);
    if (image != null) {
      width += image.getBounds().width;
      width += Tree.MARGIN_IMAGE;
    }
    return width;
  }
}

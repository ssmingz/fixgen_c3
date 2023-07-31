class PlaceHold {
  int getPreferredWidth(int columnIndex) {
    GC gc = new GC(parent);
    gc.setFont(getFont(columnIndex));
    int textPaintWidth = gc.stringExtent(getText(columnIndex)).x + (2 * MARGIN_TEXT);
    gc.dispose();
    if (columnIndex == 0) {
      return ((getTextX(columnIndex) + parent.horizontalOffset) + textPaintWidth)
          + parent.getCellPadding();
    }
    int width = (2 * parent.getCellPadding()) + textPaintWidth;
    Image image = getImage(columnIndex);
    if (image != null) {
      width += image.getBounds().width;
      width += Table.MARGIN_IMAGE;
    }
    return width;
  }
}

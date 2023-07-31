class PlaceHold {
  int getPreferredWidth(int columnIndex) {
    GC gc = new GC(parent);
    gc.setFont(getFont(columnIndex));
    int textPaintWidth = gc.stringExtent(getText(columnIndex)).x + (2 * MARGIN_TEXT);
    gc.dispose();
    int result = (getTextX(columnIndex) + textPaintWidth) + parent.getCellPadding();
    result -= parent.columns[columnIndex].getX();
    return result;
  }
}

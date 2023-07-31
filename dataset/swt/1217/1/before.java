class PlaceHold {
  public void setText(int columnIndex, String value) {
    checkWidget();
    if (value == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int validColumnCount = Math.max(1, parent.columns.length);
    if (!((0 <= columnIndex) && (columnIndex < validColumnCount))) {
      return;
    }
    if (value.equals(getText(columnIndex))) {
      return;
    }
    if (columnIndex == 0) {
      super.setText(value);
    } else {
      texts[columnIndex] = value;
    }
    int oldWidth = textWidths[columnIndex];
    GC gc = new GC(parent);
    gc.setFont(getFont(columnIndex));
    computeDisplayText(columnIndex, gc);
    textWidths[columnIndex] = gc.textExtent(getDisplayText(columnIndex)).x;
    gc.dispose();
    if (parent.columns.length == 0) {
      Rectangle bounds = getBounds();
      int rightX = bounds.x + bounds.width;
      parent.updateHorizontalBar(rightX, textWidths[columnIndex] - oldWidth);
    }
    parent.redraw(
        getTextX(columnIndex),
        parent.getItemY(this),
        Math.max(oldWidth, textWidths[columnIndex]) + (2 * MARGIN_TEXT),
        parent.itemHeight,
        false);
  }
}

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
    if (value.equals(getText(columnIndex, false))) {
      return;
    }
    if (columnIndex == 0) {
      super.setText(value);
    } else {
      texts[columnIndex] = value;
    }
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
    int oldWidth = textWidths[columnIndex];
    GC gc = new GC(parent);
    gc.setFont(getFont(columnIndex, false));
    computeDisplayText(columnIndex, gc);
    gc.dispose();
    if (parent.columns.length == 0) {
      Rectangle bounds = getBounds();
      int rightX = bounds.x + bounds.width;
      parent.updateHorizontalBar(rightX, textWidths[columnIndex] - oldWidth);
    }
    if (isInViewport()) {
      redraw(
          getTextX(columnIndex),
          parent.getItemY(this),
          Math.max(oldWidth, textWidths[columnIndex]) + (2 * MARGIN_TEXT),
          parent.itemHeight,
          columnIndex);
    }
  }
}

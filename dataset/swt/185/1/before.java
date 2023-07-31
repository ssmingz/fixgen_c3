class PlaceHold {
  public void setFont(int columnIndex, Font value) {
    checkWidget();
    if ((value != null) && value.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int validColumnCount = Math.max(1, parent.columns.length);
    if (!((0 <= columnIndex) && (columnIndex < validColumnCount))) {
      return;
    }
    if (cellFonts == null) {
      cellFonts = new Font[validColumnCount];
    }
    if (cellFonts[columnIndex] == value) {
      return;
    }
    if ((cellFonts[columnIndex] != null) && cellFonts[columnIndex].equals(value)) {
      return;
    }
    cellFonts[columnIndex] = value;
    GC gc = new GC(parent);
    gc.setFont(getFont(columnIndex));
    if (fontHeights == null) {
      fontHeights = new int[validColumnCount];
    }
    fontHeights[columnIndex] = gc.getFontMetrics().getHeight();
    computeDisplayText(columnIndex, gc);
    gc.dispose();
    if (availableIndex == (-1)) {
      return;
    }
    Rectangle bounds = getCellBounds(columnIndex);
    parent.redraw(bounds.x, bounds.y, bounds.width, bounds.height, false);
  }
}

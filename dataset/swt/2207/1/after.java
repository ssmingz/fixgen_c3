class PlaceHold {
  public void setForeground(int columnIndex, Color value) {
    checkWidget();
    if ((value != null) && value.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int validColumnCount = Math.max(1, parent.columns.length);
    if (!((0 <= columnIndex) && (columnIndex < validColumnCount))) {
      return;
    }
    if (cellForegrounds == null) {
      cellForegrounds = new Color[validColumnCount];
    }
    if (cellForegrounds[columnIndex] == value) {
      return;
    }
    if ((cellForegrounds[columnIndex] != null) && cellForegrounds[columnIndex].equals(value)) {
      return;
    }
    cellForegrounds[columnIndex] = value;
    if (availableIndex == (-1)) {
      return;
    }
    parent.redraw(
        getTextX(columnIndex),
        parent.getItemY(this),
        textWidths[columnIndex] + (2 * MARGIN_TEXT),
        parent.itemHeight,
        false);
  }
}

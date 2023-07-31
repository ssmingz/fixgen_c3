class PlaceHold {
  public void setBackground(int columnIndex, Color value) {
    checkWidget();
    if ((value != null) && value.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int validColumnCount = Math.max(1, parent.columns.length);
    if (!((0 <= columnIndex) && (columnIndex < validColumnCount))) {
      return;
    }
    if (cellBackgrounds == null) {
      cellBackgrounds = new Color[validColumnCount];
    }
    if (cellBackgrounds[columnIndex] == value) {
      return;
    }
    if ((cellBackgrounds[columnIndex] != null) && cellBackgrounds[columnIndex].equals(value)) {
      return;
    }
    cellBackgrounds[columnIndex] = value;
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
    Rectangle bounds = getCellBounds(columnIndex);
    parent.redraw(bounds.x, bounds.y, bounds.width, bounds.height, false);
  }
}

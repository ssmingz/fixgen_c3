class PlaceHold {
  Cursor getColumnResizeCursor() {
    if (columnResizeCursor == null) {
      columnResizeCursor = new Cursor(display, SWT.CURSOR_SIZEWE);
    }
    return columnResizeCursor;
  }
}

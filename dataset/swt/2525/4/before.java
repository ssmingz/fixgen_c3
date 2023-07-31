class PlaceHold {
  Cursor getColumnResizeCursor() {
    if (columnResizeCursor == null) {
      columnResizeCursor = new Cursor(getDisplay(), SWT.CURSOR_SIZEWE);
    }
    return columnResizeCursor;
  }
}

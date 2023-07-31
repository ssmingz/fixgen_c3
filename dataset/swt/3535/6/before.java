class PlaceHold {
  public void insert(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int[] start = new int[1];
    int[] end = new int[1];
    OS.XmTextGetSelectionPosition(handle, start, end);
    if (start[0] == end[0]) {
      start[0] = end[0] = OS.XmTextGetInsertionPosition(handle);
    }
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    Display display = getDisplay();
    boolean warnings = display.getWarnings();
    display.setWarnings(false);
    OS.XmTextReplace(handle, start[0], end[0], buffer);
    int position = (start[0] + buffer.length) - 1;
    OS.XmTextSetInsertionPosition(handle, position);
    display.setWarnings(warnings);
  }
}

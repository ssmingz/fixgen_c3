class PlaceHold {
  public void append(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int position = OS.XmTextGetLastPosition(handle);
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    Display display = getDisplay();
    boolean warnings = display.getWarnings();
    display.setWarnings(false);
    OS.XmTextSetInsertionPosition(handle, position);
    OS.XmTextInsert(handle, position, buffer);
    position = OS.XmTextGetLastPosition(handle);
    OS.XmTextSetInsertionPosition(handle, position);
    display.setWarnings(warnings);
  }
}

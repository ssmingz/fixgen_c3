class PlaceHold {
  public void add(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    byte[] buffer = Converter.wcsToMbcs(getCodePage(), encodeString(string), true);
    int xmString = OS.XmStringCreateLocalized(buffer);
    if (xmString == 0) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    OS.XmComboBoxAddItem(handle, xmString, -1, false);
    OS.XmStringFree(xmString);
  }
}

class PlaceHold {
  public void add(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    int xmString = OS.XmStringCreateLocalized(buffer);
    if (xmString == 0) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    OS.XmListAddItemUnselected(handle, xmString, 0);
    OS.XmStringFree(xmString);
  }
}

class PlaceHold {
  public int indexOf(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    int xmString = OS.XmStringCreateLocalized(buffer);
    if (xmString == 0) {
      return -1;
    }
    int index = OS.XmListItemPos(handle, xmString);
    OS.XmStringFree(xmString);
    return index - 1;
  }
}

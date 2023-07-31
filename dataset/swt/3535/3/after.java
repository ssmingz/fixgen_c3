class PlaceHold {
  public void remove(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    byte[] buffer = Converter.wcsToMbcs(getCodePage(), string, true);
    int xmString = OS.XmStringCreateLocalized(buffer);
    if (xmString == 0) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    int index = OS.XmListItemPos(handle, xmString);
    OS.XmStringFree(xmString);
    if (index == 0) {
      error(ERROR_INVALID_ARGUMENT);
    }
    OS.XmListDeletePos(handle, index);
  }
}

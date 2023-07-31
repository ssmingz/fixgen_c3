class PlaceHold {
  public void add(String string, int index) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (index == (-1)) {
      error(ERROR_INVALID_RANGE);
    }
    int[] argList = new int[] {OS.XmNitemCount, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    if (!((0 <= index) && (index <= argList[1]))) {
      error(ERROR_INVALID_RANGE);
    }
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    int xmString = OS.XmStringCreateLocalized(buffer);
    if (xmString == 0) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    OS.XmListAddItemUnselected(handle, xmString, index + 1);
    OS.XmStringFree(xmString);
  }
}

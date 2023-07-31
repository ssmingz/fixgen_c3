class PlaceHold {
  public void setItem(int index, String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (index == (-1)) {
      error(ERROR_INVALID_RANGE);
    }
    int[] argList = new int[] {OS.XmNitemCount, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    if (!((0 <= index) && (index < argList[1]))) {
      error(ERROR_INVALID_RANGE);
    }
    byte[] buffer = Converter.wcsToMbcs(null, string, true);
    int xmString = OS.XmStringCreateLocalized(buffer);
    if (xmString == 0) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    boolean isSelected = OS.XmListPosSelected(handle, index + 1);
    OS.XmListReplaceItemsPosUnselected(handle, new int[] {xmString}, 1, index + 1);
    if (isSelected) {
      OS.XmListSelectPos(handle, index + 1, false);
    }
    OS.XmStringFree(xmString);
  }
}

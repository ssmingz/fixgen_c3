class PlaceHold {
  public String getItem(int index) {
    checkWidget();
    int[] argList = new int[] {OS.XmNitemCount, 0, OS.XmNitems, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    if (!((0 <= index) && (index < argList[1]))) {
      error(ERROR_INVALID_RANGE);
    }
    if (argList[3] == 0) {
      error(ERROR_CANNOT_GET_ITEM);
    }
    int ptr = argList[3] + (index * 4);
    int[] buffer1 = new int[1];
    OS.memmove(buffer1, ptr, 4);
    ptr = buffer1[0];
    int address =
        OS.XmStringUnparse(ptr, null, XmCHARSET_TEXT, XmCHARSET_TEXT, null, 0, XmOUTPUT_ALL);
    if (address == 0) {
      error(ERROR_CANNOT_GET_ITEM);
    }
    int length = OS.strlen(address);
    byte[] buffer = new byte[length];
    OS.memmove(buffer, address, length);
    OS.XtFree(address);
    return new String(Converter.mbcsToWcs(null, buffer));
  }
}

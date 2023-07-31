class PlaceHold {
  public int indexOf(String string, int start) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int[] argList = new int[] {OS.XmNitems, 0, OS.XmNitemCount, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    int items = argList[1];
    int itemCount = argList[3];
    if (!((0 <= start) && (start < itemCount))) {
      return -1;
    }
    byte[] buffer1 = Converter.wcsToMbcs(null, string, true);
    int xmString = OS.XmStringCreateLocalized(buffer1);
    if (xmString == 0) {
      return -1;
    }
    int index = start;
    items += start * 4;
    int[] buffer2 = new int[1];
    while (index < itemCount) {
      OS.memmove(buffer2, items, 4);
      if (OS.XmStringCompare(buffer2[0], xmString)) {
        break;
      }
      items += 4;
      index++;
    }
    OS.XmStringFree(xmString);
    if (index == itemCount) {
      return -1;
    }
    return index;
  }
}

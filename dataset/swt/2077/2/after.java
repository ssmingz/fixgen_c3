class PlaceHold {
  public void remove(int start, int end) {
    checkWidget();
    if (start > end) {
      return;
    }
    int[] argList = new int[] {OS.XmNitemCount, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    if (!(((0 <= start) && (start <= end)) && (end < argList[1]))) {
      error(ERROR_INVALID_RANGE);
    }
    int count = (end - start) + 1;
    OS.XmListDeleteItemsPos(handle, count, start + 1);
  }
}

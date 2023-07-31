class PlaceHold {
  public void setSelection(int start, int end) {
    checkWidget();
    deselectAll();
    if (((end < 0) || (start > end)) || (((style & SWT.SINGLE) != 0) && (start != end))) {
      return;
    }
    int count = OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0);
    if ((count == 0) || (start >= count)) {
      return;
    }
    start = Math.max(0, start);
    end = Math.min(end, count - 1);
    select(start, end);
    setFocusIndex(start);
    showSelection();
  }
}

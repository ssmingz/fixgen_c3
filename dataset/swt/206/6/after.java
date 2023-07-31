class PlaceHold {
  public void select(int start, int end) {
    checkWidget();
    if (((end < 0) || (start > end)) || (((style & SWT.SINGLE) != 0) && (start != end))) {
      return;
    }
    int count = ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
    if ((count == 0) || (start >= count)) {
      return;
    }
    start = Math.max(0, start);
    end = Math.min(end, count - 1);
    if ((start == 0) && (end == (count - 1))) {
      selectAll();
    } else {
      LVITEM lvItem = new LVITEM();
      lvItem.state = OS.LVIS_SELECTED;
      lvItem.stateMask = OS.LVIS_SELECTED;
      for (int i = start; i <= end; i++) {
        ignoreSelect = true;
        OS.SendMessage(handle, LVM_SETITEMSTATE, i, lvItem);
        ignoreSelect = false;
      }
    }
  }
}

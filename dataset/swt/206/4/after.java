class PlaceHold {
  public void deselect(int start, int end) {
    checkWidget();
    int count = ((int) (OS.SendMessage(handle, LVM_GETITEMCOUNT, 0, 0)));
    if ((start == 0) && (end == (count - 1))) {
      deselectAll();
    } else {
      LVITEM lvItem = new LVITEM();
      lvItem.stateMask = OS.LVIS_SELECTED;
      start = Math.max(0, start);
      for (int i = start; i <= end; i++) {
        ignoreSelect = true;
        OS.SendMessage(handle, LVM_SETITEMSTATE, i, lvItem);
        ignoreSelect = false;
      }
    }
  }
}

class PlaceHold {
  public void deselect(int start, int end) {
    checkWidget();
    LVITEM lvItem = new LVITEM();
    lvItem.stateMask = OS.LVIS_SELECTED;
    for (int i = start; i <= end; i++) {
      ignoreSelect = true;
      OS.SendMessage(handle, LVM_SETITEMSTATE, i, lvItem);
      ignoreSelect = false;
    }
  }
}

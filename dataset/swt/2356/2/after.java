class PlaceHold {
  public void select(int start, int end) {
    checkWidget();
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

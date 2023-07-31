class PlaceHold {
  public void select(int start, int end) {
    checkWidget();
    LVITEM lvItem = new LVITEM();
    lvItem.mask = OS.LVIF_STATE;
    lvItem.state = OS.LVIS_SELECTED;
    lvItem.stateMask = OS.LVIS_SELECTED;
    for (int i = start; i <= end; i++) {
      lvItem.iItem = i;
      ignoreSelect = true;
      OS.SendMessage(handle, LVM_SETITEM, 0, lvItem);
      ignoreSelect = false;
    }
  }
}

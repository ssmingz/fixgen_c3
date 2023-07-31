class PlaceHold {
  public void deselect(int index) {
    checkWidget();
    LVITEM lvItem = new LVITEM();
    lvItem.mask = OS.LVIF_STATE;
    lvItem.stateMask = OS.LVIS_SELECTED;
    lvItem.iItem = index;
    ignoreSelect = true;
    OS.SendMessage(handle, LVM_SETITEM, 0, lvItem);
    ignoreSelect = false;
  }
}

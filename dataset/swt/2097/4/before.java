class PlaceHold {
  public void deselectAll() {
    checkWidget();
    LVITEM lvItem = new LVITEM();
    lvItem.mask = OS.LVIF_STATE;
    lvItem.stateMask = OS.LVIS_SELECTED;
    OS.SendMessage(handle, LVM_SETITEMSTATE, -1, lvItem);
  }
}

class PlaceHold {
  public void deselect(int index) {
    checkWidget();
    LVITEM lvItem = new LVITEM();
    lvItem.stateMask = OS.LVIS_SELECTED;
    ignoreSelect = true;
    OS.SendMessage(handle, LVM_SETITEMSTATE, index, lvItem);
    ignoreSelect = false;
  }
}

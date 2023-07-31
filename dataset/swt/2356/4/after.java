class PlaceHold {
  public void select(int index) {
    checkWidget();
    LVITEM lvItem = new LVITEM();
    lvItem.state = OS.LVIS_SELECTED;
    lvItem.stateMask = OS.LVIS_SELECTED;
    ignoreSelect = true;
    OS.SendMessage(handle, LVM_SETITEMSTATE, index, lvItem);
    ignoreSelect = false;
  }
}

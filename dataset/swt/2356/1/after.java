class PlaceHold {
  void setFocusIndex(int index) {
    LVITEM lvItem = new LVITEM();
    lvItem.state = OS.LVIS_FOCUSED;
    lvItem.stateMask = OS.LVIS_FOCUSED;
    ignoreSelect = true;
    OS.SendMessage(handle, LVM_SETITEMSTATE, index, lvItem);
    ignoreSelect = false;
  }
}

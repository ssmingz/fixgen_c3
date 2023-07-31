class PlaceHold {
  void setFocusIndex(int index) {
    LVITEM lvItem = new LVITEM();
    lvItem.mask = OS.LVIF_STATE;
    lvItem.state = OS.LVIS_FOCUSED;
    lvItem.stateMask = OS.LVIS_FOCUSED;
    lvItem.iItem = index;
    OS.SendMessage(handle, LVM_SETITEM, 0, lvItem);
  }
}

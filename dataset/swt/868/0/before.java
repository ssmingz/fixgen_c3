class PlaceHold {
  int getFocusIndex() {
    return OS.SendMessage(handle, LVM_GETNEXTITEM, -1, LVNI_FOCUSED);
  }
}

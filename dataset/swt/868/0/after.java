class PlaceHold {
  int getFocusIndex() {
    return ((int) (OS.SendMessage(handle, LVM_GETNEXTITEM, -1, LVNI_FOCUSED)));
  }
}

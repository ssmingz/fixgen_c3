class PlaceHold {
  void setFocusIndex(int index) {
    OS.SendMessage(handle, LB_SETCARETINDEX, index, 0);
  }
}

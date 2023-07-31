class PlaceHold {
  void setFocusIndex(int index) {
    int count = OS.SendMessage(handle, LB_GETCOUNT, 0, 0);
    if (!((0 <= index) && (index < count))) {
      return;
    }
    OS.SendMessage(handle, LB_SETCARETINDEX, index, 0);
  }
}

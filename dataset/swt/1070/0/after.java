class PlaceHold {
  int scrolledHandle() {
    if (hwndHeader == 0) {
      return handle;
    }
    int count = ((int) (OS.SendMessage(hwndHeader, HDM_GETITEMCOUNT, 0, 0)));
    return count == 0 ? handle : hwndParent;
  }
}

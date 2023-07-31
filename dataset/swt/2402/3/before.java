class PlaceHold {
  void subclass() {
    super.subclass();
    if (hwndHeader != 0) {
      OS.SetWindowLong(hwndHeader, GWL_WNDPROC, windowProc);
    }
  }
}

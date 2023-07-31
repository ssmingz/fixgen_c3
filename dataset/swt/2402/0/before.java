class PlaceHold {
  void unsubclass() {
    super.unsubclass();
    if (hwndHeader != 0) {
      OS.SetWindowLong(hwndHeader, GWL_WNDPROC, HeaderProc);
    }
  }
}

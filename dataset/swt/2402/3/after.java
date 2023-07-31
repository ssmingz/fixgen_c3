class PlaceHold {
  void subclass() {
    super.subclass();
    if (hwndHeader != 0) {
      OS.SetWindowLongPtr(hwndHeader, GWLP_WNDPROC, windowProc);
    }
  }
}

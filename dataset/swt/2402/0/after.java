class PlaceHold {
  void unsubclass() {
    super.unsubclass();
    if (hwndHeader != 0) {
      OS.SetWindowLongPtr(hwndHeader, GWLP_WNDPROC, HeaderProc);
    }
  }
}

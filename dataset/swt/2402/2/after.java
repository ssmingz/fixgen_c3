class PlaceHold {
  void subclass() {
    super.subclass();
    if (HeaderProc != 0) {
      int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
      OS.SetWindowLongPtr(hwndHeader, GWLP_WNDPROC, windowProc);
    }
  }
}

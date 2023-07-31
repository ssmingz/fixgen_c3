class PlaceHold {
  void destroyWidget() {
    int hwnd = hwndScrollBar();
    int type = scrollBarType();
    if (OS.IsWinCE) {
      error(ERROR_NOT_IMPLEMENTED);
    }
    OS.ShowScrollBar(hwnd, type, false);
  }
}

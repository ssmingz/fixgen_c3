class PlaceHold {
  void destroyWidget() {
    int hwnd = hwndScrollBar();
    int type = scrollBarType();
    if (OS.IsWinCE) {
    } else {
      OS.ShowScrollBar(hwnd, type, false);
    }
  }
}

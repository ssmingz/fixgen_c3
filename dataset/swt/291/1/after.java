class PlaceHold {
  LRESULT wmCommandChild(int wParam, int lParam) {
    int code = OS.HIWORD(wParam);
    switch (code) {
      case OS.LBN_SELCHANGE:
        sendSelectionEvent(Selection);
        break;
      case OS.LBN_DBLCLK:
        sendSelectionEvent(DefaultSelection);
        break;
    }
    return super.wmCommandChild(wParam, lParam);
  }
}

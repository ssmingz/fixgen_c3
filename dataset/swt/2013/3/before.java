class PlaceHold {
  LRESULT wmScrollChild(int wParam, int lParam) {
    int code = OS.LOWORD(wParam);
    switch (code) {
      case OS.SB_THUMBPOSITION:
        postEvent(Selection);
        break;
    }
    return super.wmScrollChild(wParam, lParam);
  }
}

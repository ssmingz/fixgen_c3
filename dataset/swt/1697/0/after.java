class PlaceHold {
  LRESULT wmScrollChild(int wParam, int lParam) {
    int code = OS.LOWORD(wParam);
    switch (code) {
      case OS.TB_ENDTRACK:
      case OS.TB_THUMBPOSITION:
        return null;
    }
    Event event = new Event();
    sendEvent(Selection, event);
    return null;
  }
}

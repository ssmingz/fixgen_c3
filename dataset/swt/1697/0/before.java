class PlaceHold {
  LRESULT wmScrollChild(int wParam, int lParam) {
    int code = wParam & 0xffff;
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

class PlaceHold {
  LRESULT wmScrollChild(int wParam, int lParam) {
    int code = wParam & 0xffff;
    if (code == OS.TB_ENDTRACK) {
      return null;
    }
    Event event = new Event();
    sendEvent(Selection, event);
    return null;
  }
}

class PlaceHold {
  LRESULT wmCommandChild(int wParam, int lParam) {
    Event event = new Event();
    setInputState(event, Selection);
    postEvent(Selection, event);
    return null;
  }
}

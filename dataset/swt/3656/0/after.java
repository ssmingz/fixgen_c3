class PlaceHold {
  LRESULT wmCommandChild(int wParam, int lParam) {
    if ((style & SWT.RADIO) != 0) {
      if ((parent.getStyle() & SWT.NO_RADIO_GROUP) == 0) {
        selectRadio();
      }
    }
    Event event = new Event();
    setInputState(event, Selection);
    postEvent(Selection, event);
    return null;
  }
}

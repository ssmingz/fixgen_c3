class PlaceHold {
  LRESULT wmCommandChild(int wParam, int lParam) {
    if ((style & (SWT.CHECK | SWT.RADIO)) != 0) {
      setSelection(!getSelection());
    }
    Event event = new Event();
    setInputState(event, Selection);
    postEvent(Selection, event);
    return null;
  }
}

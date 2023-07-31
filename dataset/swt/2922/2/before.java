class PlaceHold {
  int kEventControlHit(int nextHandler, int theEvent, int userData) {
    int result = super.kEventControlHit(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    if ((style & SWT.RADIO) != 0) {
      if ((parent.getStyle() & SWT.NO_RADIO_GROUP) == 0) {
        selectRadio();
      }
    }
    if ((style & SWT.CHECK) != 0) {
      setSelection(!getSelection());
    }
    postEvent(Selection);
    return OS.eventNotHandledErr;
  }
}

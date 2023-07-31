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
    } else if ((style & SWT.CHECK) != 0) {
      if (grayed) {
        switch (OS.GetControl32BitValue(handle)) {
          case 0:
            OS.SetControl32BitMaximum(handle, 2);
            OS.SetControl32BitValue(handle, 2);
            break;
          case 1:
          case 2:
            OS.SetControl32BitMaximum(handle, 0);
            OS.SetControl32BitValue(handle, 0);
            break;
        }
      }
    }
    postEvent(Selection);
    return OS.eventNotHandledErr;
  }
}

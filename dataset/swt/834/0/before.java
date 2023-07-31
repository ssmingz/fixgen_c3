class PlaceHold {
  int kEventControlClick(int nextHandler, int theEvent, int userData) {
    int result = super.kEventControlClick(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    int window = OS.GetControlOwner(handle);
    OS.SetKeyboardFocus(window, handle, ((short) (kControlFocusNextPart)));
    EventRecord iEvent = new EventRecord();
    OS.ConvertEventRefToEventRecord(theEvent, iEvent);
    OS.TXNClick(txnObject, iEvent);
    return OS.noErr;
  }
}

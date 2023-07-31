class PlaceHold {
  int kEventMouseDown(int nextHandler, int theEvent, int userData) {
    int[] outData = new int[1];
    OS.GetEventParameter(theEvent, kEventParamClickCount, typeUInt32, null, 4, null, outData);
    clickCount = outData[0];
    int result = super.kEventMouseDown(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    Shell shell = getShell();
    shell.bringToTop(true);
    Control oldFocus = display.getFocusControl();
    display.ignoreFocus = true;
    wasSelected = false;
    result = OS.CallNextEventHandler(nextHandler, theEvent);
    display.ignoreFocus = false;
    if (oldFocus != this) {
      if ((oldFocus != null) && (!oldFocus.isDisposed())) {
        oldFocus.sendFocusEvent(FocusOut, false);
      }
      if ((!isDisposed()) && isEnabled()) {
        sendFocusEvent(FocusIn, false);
      }
    }
    if (!wasSelected) {
      if (OS.IsDataBrowserItemSelected(handle, lastHittest)) {
        int index = lastHittest - 1;
        if ((0 <= index) && (index < itemCount)) {
          Event event = new Event();
          event.item = _getItem(index);
          postEvent(Selection, event);
        }
      }
    }
    return result;
  }
}

class PlaceHold {
  int kEventRawKeyDown(int nextHandler, int theEvent, int userData) {
    int result = super.kEventRawKeyDown(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    int[] modifiers = new int[1];
    OS.GetEventParameter(theEvent, kEventParamKeyModifiers, typeUInt32, null, 4, null, modifiers);
    if (modifiers[0] == OS.cmdKey) {
      int[] keyCode = new int[1];
      OS.GetEventParameter(
          theEvent, kEventParamKeyCode, typeUInt32, null, keyCode.length * 4, null, keyCode);
      switch (keyCode[0]) {
        case 7:
          cut();
          return OS.noErr;
        case 8:
          copy();
          return OS.noErr;
        case 9:
          paste();
          return OS.noErr;
      }
    }
    if ((style & SWT.SINGLE) != 0) {
      int[] keyCode = new int[1];
      OS.GetEventParameter(
          theEvent, kEventParamKeyCode, typeUInt32, null, keyCode.length * 4, null, keyCode);
      switch (keyCode[0]) {
        case 36:
          {
            OS.TXNFocus(txnObject, false);
            result = OS.CallNextEventHandler(nextHandler, theEvent);
            OS.TXNFocus(txnObject, true);
            postEvent(DefaultSelection);
            break;
          }
        case 48:
          {
            return OS.noErr;
          }
      }
    }
    if ((style & SWT.SINGLE) != 0) {
      return OS.noErr;
    }
    return result;
  }
}

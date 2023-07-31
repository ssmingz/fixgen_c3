class PlaceHold {
  int kEventUnicodeKeyPressed(int nextHandler, int theEvent, int userData) {
    int result = super.kEventUnicodeKeyPressed(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    int[] keyboardEvent = new int[1];
    OS.GetEventParameter(
        theEvent,
        kEventParamTextInputSendKeyboardEvent,
        typeEventRef,
        null,
        keyboardEvent.length * 4,
        null,
        keyboardEvent);
    int[] keyCode = new int[1];
    OS.GetEventParameter(
        keyboardEvent[0], kEventParamKeyCode, typeUInt32, null, keyCode.length * 4, null, keyCode);
    switch (keyCode[0]) {
      case 76:
      case 36:
        {
          sendSelectionEvent(DefaultSelection);
          break;
        }
      case 125:
      case 126:
        int[] top = new int[1];
        int[] left = new int[1];
        OS.GetDataBrowserScrollPosition(handle, top, left);
        result = OS.CallNextEventHandler(nextHandler, theEvent);
        OS.GetDataBrowserScrollPosition(handle, top, null);
        OS.SetDataBrowserScrollPosition(handle, top[0], left[0]);
    }
    return result;
  }
}

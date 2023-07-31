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
    int[] modifiers = new int[1];
    OS.GetEventParameter(
        keyboardEvent[0], kEventParamKeyModifiers, typeUInt32, null, 4, null, modifiers);
    if (modifiers[0] == OS.cmdKey) {
      int[] keyCode = new int[1];
      OS.GetEventParameter(
          keyboardEvent[0],
          kEventParamKeyCode,
          typeUInt32,
          null,
          keyCode.length * 4,
          null,
          keyCode);
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
          keyboardEvent[0],
          kEventParamKeyCode,
          typeUInt32,
          null,
          keyCode.length * 4,
          null,
          keyCode);
      switch (keyCode[0]) {
        case 76:
        case 36:
          {
            postEvent(DefaultSelection);
            return OS.noErr;
          }
        case 48:
          {
            return OS.noErr;
          }
      }
    }
    return result;
  }
}

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
    int[] modifiers = new int[1];
    OS.GetEventParameter(
        keyboardEvent[0], kEventParamKeyModifiers, typeUInt32, null, 4, null, modifiers);
    if (modifiers[0] == OS.cmdKey) {
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
    int delta = 0;
    switch (keyCode[0]) {
      case 76:
      case 36:
        sendSelectionEvent(DefaultSelection);
        return OS.noErr;
      case 116:
        delta = pageIncrement;
        break;
      case 121:
        delta = -pageIncrement;
        break;
      case 125:
        delta = -increment;
        break;
      case 126:
        delta = increment;
        break;
    }
    if (delta != 0) {
      boolean[] parseFail = new boolean[1];
      int value = getSelectionText(parseFail);
      if (parseFail[0]) {
        value = OS.GetControl32BitValue(buttonHandle);
      }
      int newValue = value + delta;
      int max = OS.GetControl32BitMaximum(buttonHandle);
      int min = OS.GetControl32BitMinimum(buttonHandle);
      if ((style & SWT.WRAP) != 0) {
        if (newValue > max) {
          newValue = min;
        }
        if (newValue < min) {
          newValue = max;
        }
      }
      newValue = Math.min(Math.max(min, newValue), max);
      if (value != newValue) {
        setSelection(newValue, true, true, true);
      }
      return OS.noErr;
    } else {
      result = OS.CallNextEventHandler(nextHandler, theEvent);
      boolean[] parseFail = new boolean[1];
      int value = getSelectionText(parseFail);
      if (!parseFail[0]) {
        int pos = OS.GetControl32BitValue(buttonHandle);
        if (pos != value) {
          setSelection(value, true, false, true);
        }
      }
    }
    return result;
  }
}

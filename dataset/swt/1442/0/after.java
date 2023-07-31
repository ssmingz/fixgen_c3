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
    String string = OS.kAXValueChangedNotification;
    char[] buffer = new char[string.length()];
    string.getChars(0, buffer.length, buffer, 0);
    int stringRef = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, buffer.length);
    OS.AXNotificationHIObjectNotify(stringRef, handle, 0);
    OS.CFRelease(stringRef);
    string = OS.kAXSelectedTextChangedNotification;
    buffer = new char[string.length()];
    string.getChars(0, buffer.length, buffer, 0);
    stringRef = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, buffer.length);
    OS.AXNotificationHIObjectNotify(stringRef, handle, 0);
    OS.CFRelease(stringRef);
    if (((hooks(Verify) || filters(Verify)) || hooks(Modify)) || filters(Modify)) {
      int[] modifiers = new int[1];
      OS.GetEventParameter(
          keyboardEvent[0], kEventParamKeyModifiers, typeUInt32, null, 4, null, modifiers);
      if (modifiers[0] == OS.cmdKey) {
        switch (keyCode[0]) {
          case 7:
            cut();
            return OS.noErr;
          case 9:
            paste();
            return OS.noErr;
        }
      }
    }
    switch (keyCode[0]) {
      case 76:
      case 36:
        {
          sendSelectionEvent(DefaultSelection);
          break;
        }
    }
    result = OS.CallNextEventHandler(nextHandler, theEvent);
    lastText = getText();
    return result;
  }
}

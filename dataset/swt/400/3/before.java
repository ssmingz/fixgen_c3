class PlaceHold {
  int kEventUnicodeKeyPressed(int nextHandler, int theEvent, int userData) {
    int result = super.kEventUnicodeKeyPressed(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    if (focusIndex == (-1)) {
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
      case 36:
      case 49:
      case 76:
        Event event = new Event();
        event.text = ids[focusIndex];
        sendEvent(Selection, event);
        break;
      case 48:
        int[] modifiers = new int[1];
        OS.GetEventParameter(
            keyboardEvent[0], kEventParamKeyModifiers, typeUInt32, null, 4, null, modifiers);
        boolean next = (modifiers[0] & OS.shiftKey) == 0;
        if (next) {
          if (focusIndex < (offsets.length - 1)) {
            focusIndex++;
            redraw();
          }
        } else if (focusIndex > 0) {
          focusIndex--;
          redraw();
        }
        break;
    }
    return result;
  }
}

class PlaceHold {
  boolean sendKeyEvent(int type, int e, boolean textInput) {
    if (textInput) {
      int text = OS.TextCompositionEventArgs_Text(e);
      if (OS.String_Length(text) == 0) {
        OS.GCHandle_Free(text);
        text = OS.TextCompositionEventArgs_SystemText(e);
        if (OS.String_Length(text) == 0) {
          OS.GCHandle_Free(text);
          text = OS.TextCompositionEventArgs_ControlText(e);
          if (OS.String_Length(text) == 0) {
            return false;
          }
        }
      }
      int chars = OS.String_ToCharArray(text);
      char[] buffer = new char[OS.String_Length(text)];
      OS.memcpy(buffer, chars, buffer.length * 2);
      OS.GCHandle_Free(chars);
      OS.GCHandle_Free(text);
      for (int i = 0; i < buffer.length; i++) {
        Event event = new Event();
        if (buffer.length == 1) {
          event.keyCode = Display.translateKey(display.lastKey);
        }
        event.character = buffer[i];
        if (display.deadChar) {
          event.character = display.lastChar;
          display.deadChar = false;
        }
        setInputState(event, type, 0, 0);
        sendEvent(type, event);
        if (isDisposed()) {
          return false;
        }
      }
      return true;
    } else {
      Event event = new Event();
      if (!setKeyState(event, type, e)) {
        return true;
      }
      sendEvent(type, event);
      if (isDisposed()) {
        return false;
      }
      return event.doit;
    }
  }
}

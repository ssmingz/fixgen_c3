class PlaceHold {
  boolean sendKeyEvent(int type, int theEvent) {
    int[] length = new int[1];
    int status =
        OS.GetEventParameter(
            theEvent, kEventParamKeyUnicodes, typeUnicodeText, null, 4, length, ((char[]) (null)));
    if ((status == OS.noErr) && (length[0] > 2)) {
      int count = 0;
      char[] chars = new char[length[0] / 2];
      OS.GetEventParameter(
          theEvent, kEventParamKeyUnicodes, typeUnicodeText, null, chars.length * 2, null, chars);
      for (int i = 0; i < chars.length; i++) {
        Event event = new Event();
        event.type = type;
        event.character = chars[i];
        setInputState(event, theEvent);
        if (sendKeyEvent(type, event)) {
          chars[count++] = chars[i];
        }
      }
      if (count == 0) {
        return false;
      }
      if (count != (chars.length - 1)) {
        OS.SetEventParameter(theEvent, kEventParamKeyUnicodes, typeUnicodeText, count * 2, chars);
      }
      return true;
    } else {
      Event event = new Event();
      event.type = type;
      setKeyState(event, theEvent);
      return sendKeyEvent(type, event);
    }
  }
}

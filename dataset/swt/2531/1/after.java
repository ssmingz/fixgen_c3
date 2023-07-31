class PlaceHold {
  boolean sendKeyEvent(int type, int theEvent) {
    if ((state & WEBKIT_EVENTS_FIX) != 0) {
      return true;
    }
    int[] length = new int[1];
    int status =
        OS.GetEventParameter(
            theEvent, kEventParamKeyUnicodes, typeUnicodeText, null, 4, length, ((char[]) (null)));
    if ((status == OS.noErr) && (length[0] > 2)) {
      int count = 0;
      int[] chord = new int[1];
      OS.GetEventParameter(theEvent, kEventParamMouseChord, typeUInt32, null, 4, null, chord);
      int[] modifiers = new int[1];
      OS.GetEventParameter(theEvent, kEventParamKeyModifiers, typeUInt32, null, 4, null, modifiers);
      char[] chars = new char[length[0] / 2];
      OS.GetEventParameter(
          theEvent, kEventParamKeyUnicodes, typeUnicodeText, null, chars.length * 2, null, chars);
      for (int i = 0; i < chars.length; i++) {
        Event event = new Event();
        event.character = chars[i];
        setInputState(event, type, chord[0], modifiers[0]);
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
      if (!setKeyState(event, type, theEvent)) {
        return true;
      }
      return sendKeyEvent(type, event);
    }
  }
}

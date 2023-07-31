class PlaceHold {
  boolean sendKeyEvent(int type, GdkEventKey keyEvent) {
    int length = keyEvent.length;
    if ((keyEvent.string == 0) || (OS.g_utf16_strlen(keyEvent.string, length) <= 1)) {
      Event event = new Event();
      event.time = keyEvent.time;
      if (!setKeyState(event, keyEvent)) {
        return true;
      }
      sendEvent(type, event);
      if (isDisposed()) {
        return false;
      }
      return event.doit;
    }
    byte[] buffer = new byte[length];
    OS.memmove(buffer, keyEvent.string, length);
    char[] chars = Converter.mbcsToWcs(null, buffer);
    return sendIMKeyEvent(type, keyEvent, chars) != null;
  }
}

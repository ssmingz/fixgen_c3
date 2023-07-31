class PlaceHold {
  boolean sendKeyEvent(int type, GdkEventKey keyEvent) {
    int length = keyEvent.length;
    if (length <= 1) {
      Event event = new Event();
      event.time = keyEvent.time;
      setKeyState(event, keyEvent);
      sendEvent(type, event);
      return event.doit;
    }
    byte[] buffer = new byte[length];
    OS.memmove(buffer, keyEvent.string, length);
    char[] chars = Converter.mbcsToWcs(null, buffer);
    return sendIMKeyEvent(type, keyEvent, chars) != null;
  }
}

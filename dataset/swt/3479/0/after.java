class PlaceHold {
  String verifyText(String string, int start, int end) {
    if ((string.length() == 0) && (start == end)) {
      return null;
    }
    Event event = new Event();
    event.text = string;
    event.start = start;
    event.end = end;
    long eventPtr = OS.gtk_get_current_event();
    if (eventPtr != 0) {
      GdkEventKey gdkEvent = new GdkEventKey();
      OS.memmove(gdkEvent, eventPtr, sizeof);
      switch (gdkEvent.type) {
        case OS.GDK_KEY_PRESS:
          setKeyState(event, gdkEvent);
          break;
      }
      OS.gdk_event_free(eventPtr);
    }
    int index = 0;
    if (OS.gtk_spin_button_get_digits(handle) > 0) {
      String decimalSeparator = getDecimalSeparator();
      index = string.indexOf(decimalSeparator);
      if (index != (-1)) {
        string = string.substring(0, index) + string.substring(index + 1);
      }
      index = 0;
    }
    if (string.length() > 0) {
      long hAdjustment = OS.gtk_spin_button_get_adjustment(handle);
      double lower = OS.gtk_adjustment_get_lower(hAdjustment);
      if ((lower < 0) && (string.charAt(0) == '-')) {
        index++;
      }
    }
    while (index < string.length()) {
      if (!Character.isDigit(string.charAt(index))) {
        break;
      }
      index++;
    }
    event.doit = index == string.length();
    sendEvent(Verify, event);
    if ((!event.doit) || isDisposed()) {
      return null;
    }
    return event.text;
  }
}

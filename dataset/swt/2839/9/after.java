class PlaceHold {
  String verifyText(String string, int start, int end, Event keyEvent) {
    Event event = new Event();
    event.text = string;
    event.start = start;
    event.end = end;
    if (keyEvent != null) {
      event.character = keyEvent.character;
      event.keyCode = keyEvent.keyCode;
      event.stateMask = keyEvent.stateMask;
    }
    int index = 0;
    if (digits > 0) {
      String decimalSeparator = getDecimalSeparator();
      index = string.indexOf(decimalSeparator);
      if (index != (-1)) {
        string = string.substring(0, index) + string.substring(index + 1);
      }
      index = 0;
    }
    while (index < string.length()) {
      if (!Character.isDigit(string.charAt(index))) {
        break;
      }
      index++;
    }
    event.doit = index == string.length();
    if ((!OS.IsUnicode) && OS.IsDBLocale) {
      event.start = mbcsToWcsPos(start);
      event.end = mbcsToWcsPos(end);
    }
    sendEvent(Verify, event);
    if ((!event.doit) || isDisposed()) {
      return null;
    }
    return event.text;
  }
}

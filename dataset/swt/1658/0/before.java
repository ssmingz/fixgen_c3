class PlaceHold {
  void mouseDidMoveOverElement(int elementInformation, int modifierFlags) {
    if (elementInformation == 0) {
      return;
    }
    int length = WebElementLinkURLKey.length();
    char[] chars = new char[length];
    WebElementLinkURLKey.getChars(0, length, chars, 0);
    int key = OS.CFStringCreateWithCharacters(0, chars, length);
    int value = Cocoa.objc_msgSend(elementInformation, S_valueForKey, key);
    OS.CFRelease(key);
    if (value == 0) {
      if (lastHoveredLinkURL == null) {
        return;
      }
      lastHoveredLinkURL = null;
      StatusTextEvent statusText = new StatusTextEvent(browser);
      statusText.display = browser.getDisplay();
      statusText.widget = browser;
      statusText.text = "";
      for (int i = 0; i < statusTextListeners.length; i++) {
        statusTextListeners[i].changed(statusText);
      }
      return;
    }
    int stringPtr = Cocoa.objc_msgSend(value, S_absoluteString);
    length = OS.CFStringGetLength(stringPtr);
    String urlString;
    if (length == 0) {
      urlString = "";
    } else {
      chars = new char[length];
      CFRange range = new CFRange();
      range.length = length;
      OS.CFStringGetCharacters(stringPtr, range, chars);
      urlString = new String(chars);
    }
    if (urlString.equals(lastHoveredLinkURL)) {
      return;
    }
    lastHoveredLinkURL = urlString;
    StatusTextEvent statusText = new StatusTextEvent(browser);
    statusText.display = browser.getDisplay();
    statusText.widget = browser;
    statusText.text = urlString;
    for (int i = 0; i < statusTextListeners.length; i++) {
      statusTextListeners[i].changed(statusText);
    }
  }
}

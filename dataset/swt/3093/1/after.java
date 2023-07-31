class PlaceHold {
  int OnStatusChange(int aWebProgress, int aRequest, int aStatus, int aMessage) {
    if (awaitingNavigate || (statusTextListeners.length == 0)) {
      return XPCOM.NS_OK;
    }
    StatusTextEvent event = new StatusTextEvent(browser);
    event.display = browser.getDisplay();
    event.widget = browser;
    int length = XPCOM.strlen_PRUnichar(aMessage);
    char[] dest = new char[length];
    XPCOM.memmove(dest, aMessage, length * 2);
    event.text = new String(dest);
    for (int i = 0; i < statusTextListeners.length; i++) {
      statusTextListeners[i].changed(event);
    }
    return XPCOM.NS_OK;
  }
}

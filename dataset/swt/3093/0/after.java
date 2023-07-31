class PlaceHold {
  int SetTitle(int aTitle) {
    if (awaitingNavigate || (titleListeners.length == 0)) {
      return XPCOM.NS_OK;
    }
    TitleEvent event = new TitleEvent(browser);
    event.display = browser.getDisplay();
    event.widget = browser;
    int length = XPCOM.strlen_PRUnichar(aTitle);
    if (length > 0) {
      char[] dest = new char[length];
      XPCOM.memmove(dest, aTitle, length * 2);
      event.title = new String(dest);
    } else {
      event.title = getUrl();
    }
    for (int i = 0; i < titleListeners.length; i++) {
      titleListeners[i].changed(event);
    }
    return XPCOM.NS_OK;
  }
}

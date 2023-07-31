class PlaceHold {
  int SetTitle(int aTitle) {
    if (titleListeners.length == 0) {
      return XPCOM.NS_OK;
    }
    TitleEvent event = new TitleEvent(this);
    event.display = getDisplay();
    event.widget = this;
    int length = XPCOM.strlen_PRUnichar(aTitle);
    char[] dest = new char[length];
    XPCOM.memmove(dest, aTitle, length * 2);
    event.title = new String(dest);
    for (int i = 0; i < titleListeners.length; i++) {
      titleListeners[i].changed(event);
    }
    return XPCOM.NS_OK;
  }
}

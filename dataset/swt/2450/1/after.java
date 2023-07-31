class PlaceHold {
  int SetStatus(int statusType, int status) {
    StatusTextEvent event = new StatusTextEvent(this);
    event.display = getDisplay();
    event.widget = this;
    int length = XPCOM.strlen_PRUnichar(status);
    char[] dest = new char[length];
    XPCOM.memmove(dest, status, length * 2);
    String string = new String(dest);
    if (string == null) {
      string = "";
    }
    event.text = string;
    for (int i = 0; i < statusTextListeners.length; i++) {
      statusTextListeners[i].changed(event);
    }
    return XPCOM.NS_OK;
  }
}

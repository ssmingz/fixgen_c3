class PlaceHold {
  void hookDOMListeners(nsIDOMEventTarget target, boolean isTop) {
    nsEmbedString string = new nsEmbedString(XPCOM.DOMEVENT_FOCUS);
    target.AddEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_UNLOAD);
    target.AddEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_MOUSEDOWN);
    target.AddEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_MOUSEUP);
    target.AddEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_MOUSEMOVE);
    target.AddEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_MOUSEDRAG);
    target.AddEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    if (isTop && delegate.hookEnterExit()) {
      string = new nsEmbedString(XPCOM.DOMEVENT_MOUSEOVER);
      target.AddEventListener(string.getAddress(), domEventListener.getAddress(), false);
      string.dispose();
      string = new nsEmbedString(XPCOM.DOMEVENT_MOUSEOUT);
      target.AddEventListener(string.getAddress(), domEventListener.getAddress(), false);
      string.dispose();
    }
    string = new nsEmbedString(XPCOM.DOMEVENT_KEYDOWN);
    target.AddEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_KEYPRESS);
    target.AddEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_KEYUP);
    target.AddEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
  }
}

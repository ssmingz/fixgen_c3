class PlaceHold {
  void unhookDOMListeners(nsIDOMEventTarget target) {
    nsEmbedString string = new nsEmbedString(XPCOM.DOMEVENT_FOCUS);
    target.RemoveEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_UNLOAD);
    target.RemoveEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_MOUSEDOWN);
    target.RemoveEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_MOUSEUP);
    target.RemoveEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_MOUSEMOVE);
    target.RemoveEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_MOUSEWHEEL);
    target.RemoveEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_MOUSEDRAG);
    target.RemoveEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_MOUSEOVER);
    target.RemoveEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_MOUSEOUT);
    target.RemoveEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_KEYDOWN);
    target.RemoveEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_KEYPRESS);
    target.RemoveEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
    string = new nsEmbedString(XPCOM.DOMEVENT_KEYUP);
    target.RemoveEventListener(string.getAddress(), domEventListener.getAddress(), false);
    string.dispose();
  }
}

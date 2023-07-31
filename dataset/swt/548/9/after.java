class PlaceHold {
  int GetInterface(int riid, int ppvObject) {
    if ((riid == 0) || (ppvObject == 0)) {
      return XPCOM.NS_ERROR_NO_INTERFACE;
    }
    nsID guid = new nsID();
    XPCOM.memmove(guid, riid, sizeof);
    if (guid.Equals(NS_IDOMWINDOW_IID)) {
      int[] aContentDOMWindow = new int[1];
      int rc = webBrowser.GetContentDOMWindow(aContentDOMWindow);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (aContentDOMWindow[0] == 0) {
        error(NS_ERROR_NO_INTERFACE);
      }
      XPCOM.memmove(ppvObject, aContentDOMWindow, PTR_SIZEOF);
      return rc;
    }
    return QueryInterface(riid, ppvObject);
  }
}

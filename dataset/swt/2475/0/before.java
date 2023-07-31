class PlaceHold {
  int QueryInterface(int riid, int ppvObject) {
    if ((riid == 0) || (ppvObject == 0)) {
      return XPCOM.NS_ERROR_NO_INTERFACE;
    }
    nsID guid = new nsID();
    XPCOM.memmove(guid, riid, sizeof);
    if (guid.Equals(NS_ISUPPORTS_IID)) {
      XPCOM.memmove(ppvObject, new int[] {supports.getAddress()}, 4);
      AddRef();
      return XPCOM.NS_OK;
    }
    if (guid.Equals(NS_IWEAKREFERENCE_IID)) {
      XPCOM.memmove(ppvObject, new int[] {weakReference.getAddress()}, 4);
      AddRef();
      return XPCOM.NS_OK;
    }
    if (guid.Equals(NS_IWEBPROGRESSLISTENER_IID)) {
      XPCOM.memmove(ppvObject, new int[] {webProgressListener.getAddress()}, 4);
      AddRef();
      return XPCOM.NS_OK;
    }
    if (guid.Equals(NS_IWEBBROWSERCHROME_IID)) {
      XPCOM.memmove(ppvObject, new int[] {webBrowserChrome.getAddress()}, 4);
      AddRef();
      return XPCOM.NS_OK;
    }
    if (guid.Equals(NS_IWEBBROWSERCHROMEFOCUS_IID)) {
      XPCOM.memmove(ppvObject, new int[] {webBrowserChromeFocus.getAddress()}, 4);
      AddRef();
      return XPCOM.NS_OK;
    }
    if (guid.Equals(NS_IEMBEDDINGSITEWINDOW_IID)) {
      XPCOM.memmove(ppvObject, new int[] {embeddingSiteWindow.getAddress()}, 4);
      AddRef();
      return XPCOM.NS_OK;
    }
    if (guid.Equals(NS_IINTERFACEREQUESTOR_IID)) {
      XPCOM.memmove(ppvObject, new int[] {interfaceRequestor.getAddress()}, 4);
      AddRef();
      return XPCOM.NS_OK;
    }
    if (guid.Equals(NS_ISUPPORTSWEAKREFERENCE_IID)) {
      XPCOM.memmove(ppvObject, new int[] {supportsWeakReference.getAddress()}, 4);
      AddRef();
      return XPCOM.NS_OK;
    }
    if (guid.Equals(NS_ICONTEXTMENULISTENER_IID)) {
      XPCOM.memmove(ppvObject, new int[] {contextMenuListener.getAddress()}, 4);
      AddRef();
      return XPCOM.NS_OK;
    }
    if (guid.Equals(NS_IURICONTENTLISTENER_IID)) {
      XPCOM.memmove(ppvObject, new int[] {uriContentListener.getAddress()}, 4);
      AddRef();
      return XPCOM.NS_OK;
    }
    XPCOM.memmove(ppvObject, new int[] {0}, 4);
    return XPCOM.NS_ERROR_NO_INTERFACE;
  }
}

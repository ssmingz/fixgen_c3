class PlaceHold {
  int QueryInterface(int riid, int ppvObject) {
    if ((riid == 0) || (ppvObject == 0)) {
      return XPCOM.NS_ERROR_NO_INTERFACE;
    }
    nsID guid = new nsID();
    XPCOM.memmove(guid, riid, sizeof);
    if (guid.Equals(NS_ISUPPORTS_IID)) {
      XPCOM.memmove(ppvObject, new int[] {supports.getAddress()}, PTR_SIZEOF);
      AddRef();
      return XPCOM.NS_OK;
    }
    if (guid.Equals(NS_IFILEPICKER_IID)) {
      XPCOM.memmove(ppvObject, new int[] {filePicker.getAddress()}, PTR_SIZEOF);
      AddRef();
      return XPCOM.NS_OK;
    }
    XPCOM.memmove(ppvObject, new int[] {0}, PTR_SIZEOF);
    return XPCOM.NS_ERROR_NO_INTERFACE;
  }
}

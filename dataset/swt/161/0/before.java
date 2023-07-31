class PlaceHold {
  public String getText() {
    int[] result = new int[1];
    int rc = webBrowser.GetContentDOMWindow(result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_NOINTERFACE);
    }
    nsIDOMWindow window = new nsIDOMWindow(result[0]);
    result[0] = 0;
    rc = window.GetDocument(result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_NOINTERFACE);
    }
    window.Release();
    int document = result[0];
    result[0] = 0;
    rc = XPCOM.NS_GetComponentManager(result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_NOINTERFACE);
    }
    nsIComponentManager componentManager = new nsIComponentManager(result[0]);
    result[0] = 0;
    byte[] contractID = MozillaDelegate.wcsToMbcs(null, NS_DOMSERIALIZER_CONTRACTID, true);
    char[] chars = null;
    rc = componentManager.CreateInstanceByContractID(contractID, 0, NS_IDOMSERIALIZER_IID, result);
    if (rc == XPCOM.NS_OK) {
      if (result[0] == 0) {
        error(NS_NOINTERFACE);
      }
      nsIDOMSerializer_1_7 serializer = new nsIDOMSerializer_1_7(result[0]);
      result[0] = 0;
      int string = XPCOM.nsEmbedString_new();
      rc = serializer.SerializeToString(document, string);
      serializer.Release();
      int length = XPCOM.nsEmbedString_Length(string);
      int buffer = XPCOM.nsEmbedString_get(string);
      chars = new char[length];
      XPCOM.memmove(chars, buffer, length * 2);
      XPCOM.nsEmbedString_delete(string);
    } else {
      rc =
          componentManager.CreateInstanceByContractID(contractID, 0, NS_IDOMSERIALIZER_IID, result);
      if (rc != XPCOM.NS_OK) {
        error(rc);
      }
      if (result[0] == 0) {
        error(NS_NOINTERFACE);
      }
      nsIDOMSerializer serializer = new nsIDOMSerializer(result[0]);
      result[0] = 0;
      rc = serializer.SerializeToString(document, result);
      serializer.Release();
      int length = XPCOM.strlen_PRUnichar(result[0]);
      chars = new char[length];
      XPCOM.memmove(chars, result[0], length * 2);
    }
    componentManager.Release();
    new nsISupports(document).Release();
    return new String(chars);
  }
}

class PlaceHold {
  public boolean execute(String script) {
    if (awaitingNavigate) {
      return false;
    }
    String url = (PREFIX_JAVASCRIPT + script) + ";void(0);";
    int[] result = new int[1];
    int rc = webBrowser.QueryInterface(NS_IWEBNAVIGATION_IID, result);
    if (rc != XPCOM.NS_OK) {
      error(rc);
    }
    if (result[0] == 0) {
      error(NS_ERROR_NO_INTERFACE);
    }
    nsIWebNavigation webNavigation = new nsIWebNavigation(result[0]);
    char[] arg = url.toCharArray();
    char[] c = new char[arg.length + 1];
    System.arraycopy(arg, 0, c, 0, arg.length);
    rc = webNavigation.LoadURI(c, LOAD_FLAGS_NONE, 0, 0, 0);
    webNavigation.Release();
    return rc == XPCOM.NS_OK;
  }
}

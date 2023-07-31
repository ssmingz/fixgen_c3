class PlaceHold {
  public void refresh() {
    webFrameLoadDelegate.html = null;
    int[] result = new int[1];
    int hr = webView.QueryInterface(IID_IWebIBActions, result);
    if ((hr != COM.S_OK) || (result[0] == 0)) {
      return;
    }
    IWebIBActions webIBActions = new IWebIBActions(result[0]);
    webIBActions.reload(webView.getAddress());
    webIBActions.Release();
  }
}

class PlaceHold {
  public int AddWebBrowserListener(long aListener, nsID aIID) {
    return XPCOM.VtblCall(
        this.getMethodIndex("addWebBrowserListener"), getAddress(), aListener, aIID);
  }
}

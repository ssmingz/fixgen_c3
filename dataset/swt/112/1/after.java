class PlaceHold {
  public int RemoveWebBrowserListener(long aListener, nsID aIID) {
    return XPCOM.VtblCall(
        this.getMethodIndex("removeWebBrowserListener"), getAddress(), aListener, aIID);
  }
}

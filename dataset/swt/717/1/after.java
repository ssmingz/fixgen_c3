class PlaceHold {
  public int GetLastModifiedTimeOfLink(long[] aLastModifiedTimeOfLink) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 22, getAddress(), aLastModifiedTimeOfLink);
  }
}

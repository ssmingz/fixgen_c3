class PlaceHold {
  public int SetFollowLinks(boolean followLinks) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 5, getAddress(), followLinks);
  }
}

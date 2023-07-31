class PlaceHold {
  public int GetFollowLinks(boolean[] followLinks) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 4, getAddress(), followLinks);
  }
}

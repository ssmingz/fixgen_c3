class PlaceHold {
  public int CopyToFollowingLinks(int newParentDir, int newName) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, getAddress(), newParentDir, newName);
  }
}

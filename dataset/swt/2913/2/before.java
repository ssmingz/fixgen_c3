class PlaceHold {
  public int CopyToFollowingLinksNative(int newParentDir, int newName) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 12, getAddress(), newParentDir, newName);
  }
}

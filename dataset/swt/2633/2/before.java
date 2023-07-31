class PlaceHold {
  public int SetFollowLinks(boolean followLinks) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 5, getAddress(), followLinks);
  }
}

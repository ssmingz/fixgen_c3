class PlaceHold {
  public int SetLastModifiedTimeOfLink(long aLastModifiedTimeOfLink) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 23, getAddress(), aLastModifiedTimeOfLink);
  }
}

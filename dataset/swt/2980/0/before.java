class PlaceHold {
  public int SetAllowImages(boolean allowImages) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 27, getAddress(), allowImages);
  }
}

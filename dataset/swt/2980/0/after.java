class PlaceHold {
  public int SetAllowImages(boolean allowImages) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 27, getAddress(), allowImages);
  }
}

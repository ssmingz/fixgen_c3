class PlaceHold {
  public int GetAllowImages(boolean[] allowImages) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 26, getAddress(), allowImages);
  }
}

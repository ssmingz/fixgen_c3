class PlaceHold {
  public int SetBlurSuppression(boolean aBlurSuppression) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 20, getAddress(), aBlurSuppression);
  }
}

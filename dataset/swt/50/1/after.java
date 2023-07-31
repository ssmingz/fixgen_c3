class PlaceHold {
  public int GetBlurSuppression(boolean[] aBlurSuppression) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 19, getAddress(), aBlurSuppression);
  }
}

class PlaceHold {
  public int SetBlurSuppression(boolean aBlurSuppression) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 20, getAddress(), aBlurSuppression);
  }
}

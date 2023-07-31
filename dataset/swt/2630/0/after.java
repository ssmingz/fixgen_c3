class PlaceHold {
  public int SetParentContentListener(int aParentContentListener) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, getAddress(), aParentContentListener);
  }
}

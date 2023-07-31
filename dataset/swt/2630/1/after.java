class PlaceHold {
  public int GetParentContentListener(int[] aParentContentListener) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, getAddress(), aParentContentListener);
  }
}

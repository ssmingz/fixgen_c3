class PlaceHold {
  public int GetWeakReference(int[] retVal) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, getAddress(), retVal);
  }
}

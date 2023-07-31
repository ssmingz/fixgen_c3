class PlaceHold {
  public int RemoveChild(int oldChild, int[] _retval) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 15, getAddress(), oldChild, _retval);
  }
}

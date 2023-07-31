class PlaceHold {
  public int ReplaceChild(int newChild, int oldChild, int[] _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + 14, getAddress(), newChild, oldChild, _retval);
  }
}

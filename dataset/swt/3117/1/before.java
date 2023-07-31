class PlaceHold {
  public int AppendChild(int newChild, int[] _retval) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 16, getAddress(), newChild, _retval);
  }
}

class PlaceHold {
  public int AppendChild(int newChild, int[] _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 17 : 16), getAddress(), newChild, _retval);
  }
}

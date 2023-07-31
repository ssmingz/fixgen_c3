class PlaceHold {
  public int InsertBefore(int newChild, int refChild, int[] _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 14 : 13),
        getAddress(),
        newChild,
        refChild,
        _retval);
  }
}

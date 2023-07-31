class PlaceHold {
  public int ShowModalDialog(long aURI, long aArgs, long aOptions, long[] _retval) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + 32, getAddress(), aURI, aArgs, aOptions, _retval);
  }
}

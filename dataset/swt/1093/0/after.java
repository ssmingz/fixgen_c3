class PlaceHold {
  public int NewFileURI(int aFile, int[] _retval) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, getAddress(), aFile, _retval);
  }
}

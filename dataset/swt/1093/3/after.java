class PlaceHold {
  public int Equals(int inFile, boolean[] _retval) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 42, getAddress(), inFile, _retval);
  }
}

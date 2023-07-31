class PlaceHold {
  public int OpenNSPRFileDesc(int flags, int mode, int[] _retval) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 6, getAddress(), flags, mode, _retval);
  }
}

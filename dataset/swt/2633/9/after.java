class PlaceHold {
  public int OpenANSIFileDesc(byte[] mode, int[] _retval) {
    return XPCOM.VtblCall(nsIFile.LAST_METHOD_ID + 7, getAddress(), mode, _retval);
  }
}

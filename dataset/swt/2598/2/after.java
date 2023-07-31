class PlaceHold {
  public int Open(int url, int name, int options, int[] _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 60, getAddress(), url, name, options, _retval);
  }
}

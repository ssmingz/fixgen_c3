class PlaceHold {
  public int GetFiles(byte[] prop, int[] _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 1, getAddress(), prop, _retval);
  }
}

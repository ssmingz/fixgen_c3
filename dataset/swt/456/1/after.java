class PlaceHold {
  public int EnumerateObservers(byte[] aTopic, int[] _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 4, getAddress(), aTopic, _retval);
  }
}

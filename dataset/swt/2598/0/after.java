class PlaceHold {
  public int FindItemWithName(char[] name, int aRequestor, int[] _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 11, getAddress(), name, aRequestor, _retval);
  }
}

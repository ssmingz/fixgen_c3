class PlaceHold {
  public int ReadSegments(int aWriter, int aClosure, int aCount, int[] _retval) {
    return XPCOM.VtblCall(
        super.LAST_METHOD_ID + 4, getAddress(), aWriter, aClosure, aCount, _retval);
  }
}

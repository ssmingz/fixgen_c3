class PlaceHold {
  public int Subsumes(long other, int[] _retval) {
    return XPCOM.VtblCall(
        nsISerializable.LAST_METHOD_ID + (IsXULRunner10 ? 21 : 20), getAddress(), other, _retval);
  }
}

class PlaceHold {
  public int GetControllerAt(int index, int[] _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 6, getAddress(), index, _retval);
  }
}

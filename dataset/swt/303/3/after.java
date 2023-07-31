class PlaceHold {
  public int ToString(int _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 20, getAddress(), _retval);
  }
}

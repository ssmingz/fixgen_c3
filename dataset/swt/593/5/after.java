class PlaceHold {
  public int ProfileExists(char[] profileName, boolean[] _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 3, getAddress(), profileName, _retval);
  }
}

class PlaceHold {
  public int SupportsCommand(byte[] command, boolean[] _retval) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 2, getAddress(), command, _retval);
  }
}

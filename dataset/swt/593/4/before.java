class PlaceHold {
  public int SupportsCommand(byte[] command, boolean[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 2, getAddress(), command, retVal);
  }
}

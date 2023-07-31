class PlaceHold {
  public int IsCommandEnabled(byte[] command, boolean[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 1, getAddress(), command, retVal);
  }
}

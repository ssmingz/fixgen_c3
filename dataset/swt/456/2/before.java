class PlaceHold {
  public int GetFiles(byte[] prop, int[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 1, getAddress(), prop, retVal);
  }
}

class PlaceHold {
  public int Load(int[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 8, getAddress(), retVal);
  }
}

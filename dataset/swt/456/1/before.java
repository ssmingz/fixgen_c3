class PlaceHold {
  public int EnumerateObservers(byte[] aTopic, int[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 4, getAddress(), aTopic, retVal);
  }
}

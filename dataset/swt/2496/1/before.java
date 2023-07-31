class PlaceHold {
  public int EnumerateContractIDs(int[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 10, getAddress(), retVal);
  }
}

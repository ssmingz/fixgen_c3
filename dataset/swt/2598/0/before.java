class PlaceHold {
  public int FindItemWithName(char[] name, int aRequestor, int[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 11, getAddress(), name, aRequestor, retVal);
  }
}

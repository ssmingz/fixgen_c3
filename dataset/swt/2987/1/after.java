class PlaceHold {
  public int EnumerateContractIDs(int[] _retval) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, getAddress(), _retval);
  }
}

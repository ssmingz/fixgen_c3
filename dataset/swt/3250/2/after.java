class PlaceHold {
  public int CIDToContractID(nsID aClass, int[] _retval) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, getAddress(), aClass, _retval);
  }
}

class PlaceHold {
  public int Available(int[] _retval) {
    if (IsXULRVersionOrLater(VERSION_XR24)) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(this.getMethodIndex("available"), getAddress(), _retval);
  }
}

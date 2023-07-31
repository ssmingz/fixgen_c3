class PlaceHold {
  public int GetJSPrincipals(long cx, long[] _retval) {
    if (IsXULRVersionOrLater(24)) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(this.getMethodIndex("getJSPrincipals"), getAddress(), cx, _retval);
  }
}

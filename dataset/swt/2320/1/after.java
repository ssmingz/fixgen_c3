class PlaceHold {
  public int GetSystemPrincipal(long[] _retval) {
    return XPCOM.VtblCall(this.getMethodIndex("getSystemPrincipal"), getAddress(), _retval);
  }
}

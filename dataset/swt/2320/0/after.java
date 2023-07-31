class PlaceHold {
  public int GetSubjectPrincipal(long[] _retval) {
    return XPCOM.VtblCall(this.getMethodIndex("getSubjectPrincipal"), getAddress(), _retval);
  }
}

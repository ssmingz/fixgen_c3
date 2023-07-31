class PlaceHold {
  public int GetIntPref(byte[] aPrefName, int[] _retval) {
    return XPCOM.VtblCall(this.getMethodIndex("getIntPref"), getAddress(), aPrefName, _retval);
  }
}

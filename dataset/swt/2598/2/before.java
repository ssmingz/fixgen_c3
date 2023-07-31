class PlaceHold {
  public int Open(int url, int name, int options, int[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 60, getAddress(), url, name, options, retVal);
  }
}

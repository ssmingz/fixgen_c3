class PlaceHold {
  public int OpenNSPRFileDesc(int flags, int mode, int[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 6, getAddress(), flags, mode, retVal);
  }
}

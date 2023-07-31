class PlaceHold {
  public int ToString(int retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 20, getAddress(), retVal);
  }
}

class PlaceHold {
  public int GetControllerCount(int[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 11, getAddress(), retVal);
  }
}

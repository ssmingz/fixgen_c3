class PlaceHold {
  public int OnLocationChange(int aWebProgress, int aRequest, int location) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 3, getAddress(), aWebProgress, aRequest, location);
  }
}

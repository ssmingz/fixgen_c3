class PlaceHold {
  public int GetControllerAt(int index, int[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 6, getAddress(), index, retVal);
  }
}

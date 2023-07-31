class PlaceHold {
  public int RemoveControllerAt(int index, int[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 5, getAddress(), index, retVal);
  }
}

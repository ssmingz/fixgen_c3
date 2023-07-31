class PlaceHold {
  public int GetWeakReference(int[] retVal) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 1, getAddress(), retVal);
  }
}

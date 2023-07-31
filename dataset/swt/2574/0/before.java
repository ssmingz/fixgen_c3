class PlaceHold {
  public int AppendNative(int node) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 2, getAddress(), node);
  }
}

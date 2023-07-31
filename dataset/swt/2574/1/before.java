class PlaceHold {
  public int Append(int node) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 1, getAddress(), node);
  }
}

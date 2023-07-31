class PlaceHold {
  public int Append(int node) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, getAddress(), node);
  }
}

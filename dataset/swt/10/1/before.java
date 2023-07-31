class PlaceHold {
  public int GetNextSibling(int[] aNextSibling) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, getAddress(), aNextSibling);
  }
}

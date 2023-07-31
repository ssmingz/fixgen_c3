class PlaceHold {
  public int GetPreviousSibling(int[] aPreviousSibling) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, getAddress(), aPreviousSibling);
  }
}

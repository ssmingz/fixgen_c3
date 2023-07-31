class PlaceHold {
  public int GetLeafName(int aLeafName) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, getAddress(), aLeafName);
  }
}

class PlaceHold {
  public int SetLeafName(int aLeafName) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, getAddress(), aLeafName);
  }
}

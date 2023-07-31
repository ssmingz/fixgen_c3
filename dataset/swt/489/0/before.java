class PlaceHold {
  public int GetLeafName(int aLeafName) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 5, getAddress(), aLeafName);
  }
}

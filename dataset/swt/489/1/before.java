class PlaceHold {
  public int SetLeafName(int aLeafName) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 6, getAddress(), aLeafName);
  }
}

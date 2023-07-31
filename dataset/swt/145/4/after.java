class PlaceHold {
  public int GetAltKey(int[] aAltKey) {
    return XPCOM.VtblCall(this.getGetterIndex("altKey"), getAddress(), aAltKey);
  }
}

class PlaceHold {
  public int GetCharCode(int[] aCharCode) {
    return XPCOM.VtblCall(this.getGetterIndex("charCode"), getAddress(), aCharCode);
  }
}

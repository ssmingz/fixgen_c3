class PlaceHold {
  public int GetKeyCode(int[] aKeyCode) {
    return XPCOM.VtblCall(this.getGetterIndex("keyCode"), getAddress(), aKeyCode);
  }
}

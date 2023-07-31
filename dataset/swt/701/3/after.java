class PlaceHold {
  public int GetShiftKey(int[] aShiftKey) {
    return XPCOM.VtblCall(this.getGetterIndex("shiftKey"), getAddress(), aShiftKey);
  }
}

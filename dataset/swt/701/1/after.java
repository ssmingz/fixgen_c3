class PlaceHold {
  public int GetCtrlKey(int[] aCtrlKey) {
    return XPCOM.VtblCall(this.getGetterIndex("ctrlKey"), getAddress(), aCtrlKey);
  }
}

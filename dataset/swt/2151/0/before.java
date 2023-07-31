class PlaceHold {
  public int GetDetail(int[] aDetail) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 2, getAddress(), aDetail);
  }
}

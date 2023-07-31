class PlaceHold {
  public int GetDetail(int[] aDetail) {
    return XPCOM.VtblCall(nsIDOMEvent.LAST_METHOD_ID + 2, getAddress(), aDetail);
  }
}

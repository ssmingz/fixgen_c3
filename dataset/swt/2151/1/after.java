class PlaceHold {
  public int GetView(int[] aView) {
    return XPCOM.VtblCall(nsIDOMEvent.LAST_METHOD_ID + 1, getAddress(), aView);
  }
}

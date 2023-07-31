class PlaceHold {
  public int GetCtrlKey(int[] aCtrlKey) {
    return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 5, getAddress(), aCtrlKey);
  }
}

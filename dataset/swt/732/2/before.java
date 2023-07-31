class PlaceHold {
  public int GetCtrlKey(int[] aCtrlKey) {
    return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 4, getAddress(), aCtrlKey);
  }
}

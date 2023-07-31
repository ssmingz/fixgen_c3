class PlaceHold {
  public int GetCtrlKey(boolean[] aCtrlKey) {
    return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 5, getAddress(), aCtrlKey);
  }
}

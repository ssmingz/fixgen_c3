class PlaceHold {
  public int GetCtrlKey(int[] aCtrlKey) {
    return XPCOM.VtblCall(
        nsIDOMUIEvent.LAST_METHOD_ID + (IsXULRunner24() ? 7 : 5), getAddress(), aCtrlKey);
  }
}

class PlaceHold {
  public int GetKeyCode(int[] aKeyCode) {
    return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 2, getAddress(), aKeyCode);
  }
}

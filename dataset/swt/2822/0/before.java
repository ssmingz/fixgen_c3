class PlaceHold {
  public int GetAltKey(int[] aAltKey) {
    return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 7, getAddress(), aAltKey);
  }
}

class PlaceHold {
  public int GetCharCode(int[] aCharCode) {
    return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 1, getAddress(), aCharCode);
  }
}

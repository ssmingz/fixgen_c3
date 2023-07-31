class PlaceHold {
  public int GetShiftKey(int[] aShiftKey) {
    return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 5, getAddress(), aShiftKey);
  }
}

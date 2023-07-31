class PlaceHold {
  public int GetShiftKey(boolean[] aShiftKey) {
    return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 6, getAddress(), aShiftKey);
  }
}

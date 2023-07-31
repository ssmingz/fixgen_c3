class PlaceHold {
  public int GetShiftKey(int[] aShiftKey) {
    return XPCOM.VtblCall(
        nsIDOMUIEvent.LAST_METHOD_ID + (IsXULRunner24() ? 8 : 6), getAddress(), aShiftKey);
  }
}

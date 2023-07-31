class PlaceHold {
  public int GetAltKey(int[] aAltKey) {
    return XPCOM.VtblCall(
        nsIDOMUIEvent.LAST_METHOD_ID + (IsXULRunner24() ? 9 : 7), getAddress(), aAltKey);
  }
}

class PlaceHold {
  public int GetMetaKey(int[] aMetaKey) {
    return XPCOM.VtblCall(
        nsIDOMUIEvent.LAST_METHOD_ID + (IsXULRunner24() ? 10 : 8), getAddress(), aMetaKey);
  }
}

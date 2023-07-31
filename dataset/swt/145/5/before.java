class PlaceHold {
  public int GetRelatedTarget(long[] aRelatedTarget) {
    return XPCOM.VtblCall(
        nsIDOMUIEvent.LAST_METHOD_ID + (IsXULRunner24() ? 13 : 10), getAddress(), aRelatedTarget);
  }
}

class PlaceHold {
  public int GetMetaKey(int[] aMetaKey) {
    return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 6, getAddress(), aMetaKey);
  }
}

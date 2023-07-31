class PlaceHold {
  public int GetMetaKey(boolean[] aMetaKey) {
    return XPCOM.VtblCall(nsIDOMUIEvent.LAST_METHOD_ID + 8, getAddress(), aMetaKey);
  }
}

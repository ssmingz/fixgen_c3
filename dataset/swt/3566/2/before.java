class PlaceHold {
  public int GetMetaKey(boolean[] aMetaKey) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 8, getAddress(), aMetaKey);
  }
}

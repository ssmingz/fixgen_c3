class PlaceHold {
  public int GetMetaKey(int[] aMetaKey) {
    return XPCOM.VtblCall(this.getGetterIndex("metaKey"), getAddress(), aMetaKey);
  }
}

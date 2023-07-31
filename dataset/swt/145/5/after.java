class PlaceHold {
  public int GetRelatedTarget(long[] aRelatedTarget) {
    return XPCOM.VtblCall(this.getGetterIndex("relatedTarget"), getAddress(), aRelatedTarget);
  }
}

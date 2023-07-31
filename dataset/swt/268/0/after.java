class PlaceHold {
  public int SetParentURIContentListener(long aParentURIContentListener) {
    return XPCOM.VtblCall(
        this.getSetterIndex("parentURIContentListener"), getAddress(), aParentURIContentListener);
  }
}

class PlaceHold {
  public int GetFrames(long[] aFrames) {
    return XPCOM.VtblCall(this.getGetterIndex("frames"), getAddress(), aFrames);
  }
}

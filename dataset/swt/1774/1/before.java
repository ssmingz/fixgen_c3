class PlaceHold {
  public int GetFrames(int[] frames) {
    return XPCOM.VtblCall(super.LAST_METHOD_ID + 5, getAddress(), frames);
  }
}

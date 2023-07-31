class PlaceHold {
  public int GetFrames(int[] aFrames) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + (Is8 ? 62 : 5), getAddress(), aFrames);
  }
}

class PlaceHold {
  public int GetFrames(int[] aFrames) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 62 : 5), getAddress(), aFrames);
  }
}

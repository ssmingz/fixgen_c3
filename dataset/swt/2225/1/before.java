class PlaceHold {
  public int GetFrames(long[] aFrames) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner24() ? 66 : IsXULRunner10() ? 62 : 5),
        getAddress(),
        aFrames);
  }
}

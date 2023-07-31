class PlaceHold {
  public int GetTextZoom(float[] aTextZoom) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 63 : 8), getAddress(), aTextZoom);
  }
}

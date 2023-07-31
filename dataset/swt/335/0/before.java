class PlaceHold {
  public int GetTextZoom(float[] aTextZoom) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 63, getAddress(), aTextZoom);
  }
}

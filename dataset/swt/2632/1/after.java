class PlaceHold {
  public int GetTextZoom(float[] aTextZoom) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + (Is8 ? 63 : 8), getAddress(), aTextZoom);
  }
}

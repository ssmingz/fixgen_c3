class PlaceHold {
  public int SetTextZoom(float aTextZoom) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + (Is8 ? 64 : 9), getAddress(), aTextZoom);
  }
}

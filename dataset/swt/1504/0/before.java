class PlaceHold {
  public int SetTextZoom(float aTextZoom) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 64 : 9), getAddress(), aTextZoom);
  }
}

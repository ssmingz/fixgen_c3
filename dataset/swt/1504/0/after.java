class PlaceHold {
  public int SetTextZoom(float aTextZoom) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 67 : IsXULRunner10 ? 64 : 9),
        getAddress(),
        aTextZoom);
  }
}

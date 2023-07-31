class PlaceHold {
  public int GetMainWidget(long[] aMainWidget) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 20 : IsXULRunner10 ? 19 : 21),
        getAddress(),
        aMainWidget);
  }
}

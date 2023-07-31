class PlaceHold {
  public int Scroll(int xScroll, int yScroll) {
    if (!IsXULRunner10) {
      return XPCOM.NS_COMFALSE;
    }
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 52 : 49), getAddress(), xScroll, yScroll);
  }
}

class PlaceHold {
  public int ScrollBy(int xScrollDif, int yScrollDif) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 54 : IsXULRunner10 ? 51 : 13),
        getAddress(),
        xScrollDif,
        yScrollDif);
  }
}

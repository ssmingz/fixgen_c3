class PlaceHold {
  public int SetFocus() {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 21 : IsXULRunner10 ? 20 : 22), getAddress());
  }
}

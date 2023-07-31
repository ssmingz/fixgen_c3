class PlaceHold {
  public int Show(long _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner17 ? 17 : 15), getAddress(), _retval);
  }
}

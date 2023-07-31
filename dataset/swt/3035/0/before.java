class PlaceHold {
  public int Show(int _retval) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 15, getAddress(), _retval);
  }
}

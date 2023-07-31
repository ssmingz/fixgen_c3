class PlaceHold {
  public int HasChildNodes(int[] _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + (IsXULRunner10 ? 18 : 17), getAddress(), _retval);
  }
}

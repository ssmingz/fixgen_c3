class PlaceHold {
  public int Confirm(int parent, char[] dialogTitle, char[] text, boolean[] _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + 3, getAddress(), parent, dialogTitle, text, _retval);
  }
}

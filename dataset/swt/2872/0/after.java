class PlaceHold {
  public int Confirm(int aParent, char[] aDialogTitle, char[] aText, boolean[] _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + 3, getAddress(), aParent, aDialogTitle, aText, _retval);
  }
}

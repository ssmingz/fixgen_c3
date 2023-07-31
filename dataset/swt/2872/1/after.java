class PlaceHold {
  public int Alert(int aParent, char[] aDialogTitle, char[] aText) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + 1, getAddress(), aParent, aDialogTitle, aText);
  }
}

class PlaceHold {
  public int Alert(int parent, char[] dialogTitle, char[] text) {
    return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, getAddress(), parent, dialogTitle, text);
  }
}

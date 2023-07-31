class PlaceHold {
  public int AlertCheck(
      int parent, char[] dialogTitle, char[] text, char[] checkMsg, boolean[] checkValue) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + 2,
        getAddress(),
        parent,
        dialogTitle,
        text,
        checkMsg,
        checkValue);
  }
}

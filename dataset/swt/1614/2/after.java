class PlaceHold {
  public int ConfirmCheck(
      int parent,
      char[] dialogTitle,
      char[] text,
      char[] checkMsg,
      boolean[] checkValue,
      boolean[] _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + 4,
        getAddress(),
        parent,
        dialogTitle,
        text,
        checkMsg,
        checkValue,
        _retval);
  }
}

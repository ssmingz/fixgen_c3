class PlaceHold {
  public int PromptPassword(
      int parent,
      char[] dialogTitle,
      char[] text,
      int[] password,
      char[] checkMsg,
      boolean[] checkValue,
      boolean[] _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + 8,
        getAddress(),
        parent,
        dialogTitle,
        text,
        password,
        checkMsg,
        checkValue,
        _retval);
  }
}

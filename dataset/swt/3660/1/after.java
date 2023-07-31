class PlaceHold {
  public int PromptUsernameAndPassword(
      int parent,
      char[] dialogTitle,
      char[] text,
      int[] username,
      int[] password,
      char[] checkMsg,
      boolean[] checkValue,
      boolean[] _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + 7,
        getAddress(),
        parent,
        dialogTitle,
        text,
        username,
        password,
        checkMsg,
        checkValue,
        _retval);
  }
}

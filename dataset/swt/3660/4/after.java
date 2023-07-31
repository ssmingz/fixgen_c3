class PlaceHold {
  public int Prompt(
      int parent,
      char[] dialogTitle,
      char[] text,
      int[] value,
      char[] checkMsg,
      boolean[] checkValue,
      boolean[] _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + 6,
        getAddress(),
        parent,
        dialogTitle,
        text,
        value,
        checkMsg,
        checkValue,
        _retval);
  }
}

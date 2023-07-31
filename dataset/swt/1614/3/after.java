class PlaceHold {
  public int ConfirmEx(
      int parent,
      char[] dialogTitle,
      char[] text,
      int buttonFlags,
      char[] button0Title,
      char[] button1Title,
      char[] button2Title,
      char[] checkMsg,
      boolean[] checkValue,
      int[] _retval) {
    return XPCOM.VtblCall(
        nsISupports.LAST_METHOD_ID + 5,
        getAddress(),
        parent,
        dialogTitle,
        text,
        buttonFlags,
        button0Title,
        button1Title,
        button2Title,
        checkMsg,
        checkValue,
        _retval);
  }
}

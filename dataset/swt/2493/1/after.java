class PlaceHold {
  public void setEchoChar(char echo) {
    checkWidget();
    if ((style & SWT.MULTI) != 0) {
      return;
    }
    if (txnObject == 0) {
      if ((style & SWT.PASSWORD) == 0) {
        Point selection = getSelection();
        char[] text = getTextChars();
        echoCharacter = echo;
        setEditText(text);
        setSelection(selection);
      }
    } else {
      OS.TXNEchoMode(txnObject, echo, kTextEncodingMacUnicode, echo != '\u0000');
    }
    echoCharacter = echo;
  }
}

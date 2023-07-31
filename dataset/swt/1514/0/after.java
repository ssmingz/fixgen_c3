class PlaceHold {
  public void setEchoChar(char echo) {
    checkWidget();
    if ((style & SWT.MULTI) != 0) {
      return;
    }
    echoCharacter = echo;
    OS.TXNEchoMode(txnObject, echo, 0, echo != '\u0000');
  }
}

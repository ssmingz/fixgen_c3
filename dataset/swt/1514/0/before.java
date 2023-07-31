class PlaceHold {
  public void setEchoChar(char echo) {
    checkWidget();
    echoCharacter = echo;
    OS.TXNEchoMode(txnObject, echo, 0, echo != '\u0000');
  }
}

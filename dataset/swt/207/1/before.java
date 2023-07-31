class PlaceHold {
  public void setEchoChar(char echo) {
    checkWidget();
    if (echoCharacter == echo) {
      return;
    }
    echoCharacter = echo;
    syncBounds();
    OS.TXNEchoMode(tx, echo, 0, echo != '\u0000');
  }
}

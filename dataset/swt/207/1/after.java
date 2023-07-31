class PlaceHold {
  public void setEchoChar(char echo) {
    checkWidget();
    if (echoCharacter == echo) {
      return;
    }
    echoCharacter = echo;
    OS.TXNEchoMode(fTX, echo, 0, echo != '\u0000');
  }
}

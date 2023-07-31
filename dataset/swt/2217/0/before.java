class PlaceHold {
  public void setEchoChar(char echo) {
    checkWidget();
    if ((style & SWT.MULTI) != 0) {
      return;
    }
    if ((style & SWT.PASSWORD) == 0) {
      Point selection = getSelection();
      String text = getText();
      echoCharacter = echo;
      setEditText(text);
      setSelection(selection);
    }
    echoCharacter = echo;
  }
}

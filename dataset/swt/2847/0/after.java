class PlaceHold {
  public void setEchoChar(char echo) {
    checkWidget();
    if ((style & SWT.MULTI) != 0) {
      return;
    }
    if ((style & SWT.PASSWORD) == 0) {
      Point selection = getSelection();
      char[] text = getTextChars();
      echoCharacter = echo;
      setEditText(text);
      setSelection(selection);
    }
    echoCharacter = echo;
  }
}

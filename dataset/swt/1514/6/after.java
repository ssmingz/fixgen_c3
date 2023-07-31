class PlaceHold {
  public void setEchoChar(char echo) {
    checkWidget();
    if ((style & SWT.MULTI) != 0) {
      return;
    }
    if (echoCharacter == echo) {
      return;
    }
    String newText;
    if (echo == 0) {
      newText = hiddenText;
      hiddenText = "";
    } else {
      newText = hiddenText = getText();
    }
    echoCharacter = echo;
    Point selection = getSelection();
    boolean oldValue = ignoreChange;
    ignoreChange = true;
    setText(newText);
    setSelection(selection);
    ignoreChange = oldValue;
  }
}

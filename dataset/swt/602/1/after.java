class PlaceHold {
  public void copy() {
    checkWidget();
    if (((style & SWT.PASSWORD) != 0) || (echoCharacter != '\u0000')) {
      return;
    }
    if (txnObject == 0) {
      Point selection = getSelection();
      if (selection.x == selection.y) {
        return;
      }
      copyToClipboard(getEditText(selection.x, selection.y - 1));
    } else {
      OS.TXNCopy(txnObject);
    }
  }
}

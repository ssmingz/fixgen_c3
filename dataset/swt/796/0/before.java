class PlaceHold {
  public void insert(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (hooks(Verify) || filters(Verify)) {
      Point selection = getSelection();
      string = verifyText(string, selection.x, selection.y);
      if (string == null) {
        return;
      }
    }
    setTXNText(kTXNUseCurrentSelection, kTXNUseCurrentSelection, string);
    OS.TXNShowSelection(txnObject, false);
    if (string.length() != 0) {
      sendEvent(Modify);
    }
  }
}

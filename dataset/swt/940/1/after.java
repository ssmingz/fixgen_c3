class PlaceHold {
  public void insert(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (hooks(Verify) || filters(Verify)) {
      Point selection = getSelection();
      string = verifyText(string, selection.x, selection.y, null);
      if (string == null) {
        return;
      }
    }
    if (txnObject == 0) {
      insertEditText(string);
    } else {
      setTXNText(kTXNUseCurrentSelection, kTXNUseCurrentSelection, string);
      OS.TXNShowSelection(txnObject, false);
    }
    if (string.length() != 0) {
      sendModifyEvent(true);
    }
  }
}

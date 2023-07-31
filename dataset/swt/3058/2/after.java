class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (hooks(Verify) || filters(Verify)) {
      string = verifyText(string, 0, getCharCount());
      if (string == null) {
        return;
      }
    }
    setTXNText(kTXNStartOffset, kTXNEndOffset, string);
    OS.TXNSetSelection(txnObject, kTXNStartOffset, kTXNStartOffset);
    OS.TXNShowSelection(txnObject, false);
    sendEvent(Modify);
  }
}

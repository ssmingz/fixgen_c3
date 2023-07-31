class PlaceHold {
  public void append(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (hooks(Verify) || filters(Verify)) {
      int charCount = getCharCount();
      string = verifyText(string, charCount, charCount);
      if (string == null) {
        return;
      }
    }
    setTXNText(kTXNEndOffset, kTXNEndOffset, string);
    OS.TXNSetSelection(txnObject, kTXNEndOffset, kTXNEndOffset);
    OS.TXNShowSelection(txnObject, false);
    if (string.length() != 0) {
      sendEvent(Modify);
    }
  }
}

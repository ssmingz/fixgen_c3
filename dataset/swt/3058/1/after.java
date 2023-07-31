class PlaceHold {
  public void paste() {
    checkWidget();
    OS.TXNPaste(txnObject);
    sendEvent(Modify);
  }
}

class PlaceHold {
  public void paste() {
    checkWidget();
    syncBounds();
    OS.TXNPaste(tx);
  }
}

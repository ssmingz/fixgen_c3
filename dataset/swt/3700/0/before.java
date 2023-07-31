class PlaceHold {
  public void clearSelection() {
    checkWidget();
    syncBounds();
    OS.TXNSetSelection(tx, kTXNStartOffset, kTXNStartOffset);
  }
}

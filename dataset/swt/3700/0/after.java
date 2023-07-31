class PlaceHold {
  public void clearSelection() {
    checkWidget();
    OS.TXNSetSelection(fTX, kTXNStartOffset, kTXNStartOffset);
  }
}

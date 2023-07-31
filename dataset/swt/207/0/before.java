class PlaceHold {
  public void setSelection(int start, int end) {
    checkWidget();
    syncBounds();
    OS.TXNSetSelection(tx, start, end);
  }
}

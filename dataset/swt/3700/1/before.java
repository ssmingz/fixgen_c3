class PlaceHold {
  public void setSelection(int start) {
    checkWidget();
    syncBounds();
    OS.TXNSetSelection(tx, start, start);
  }
}

class PlaceHold {
  public void selectAll() {
    checkWidget();
    syncBounds();
    OS.TXNSelectAll(tx);
  }
}

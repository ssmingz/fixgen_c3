class PlaceHold {
  public void showSelection() {
    checkWidget();
    syncBounds();
    OS.TXNShowSelection(tx, false);
  }
}

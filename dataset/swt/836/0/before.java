class PlaceHold {
  public void setSelection(int start, int end) {
    checkWidget();
    syncBounds();
    OS.TXNSetSelection(fTX, start, end);
  }
}

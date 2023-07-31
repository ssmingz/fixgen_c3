class PlaceHold {
  public void setSelection(int start, int end) {
    checkWidget();
    OS.TXNSetSelection(fTX, start, end);
  }
}

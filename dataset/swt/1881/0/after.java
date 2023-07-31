class PlaceHold {
  public void setSelection(int start) {
    checkWidget();
    OS.TXNSetSelection(fTX, start, start);
  }
}

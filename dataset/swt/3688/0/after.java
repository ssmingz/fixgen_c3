class PlaceHold {
  public int getCharCount() {
    checkWidget();
    return OS.TXNDataSize(txnObject) / 2;
  }
}

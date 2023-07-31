class PlaceHold {
  public int getCharCount() {
    checkWidget();
    return OS.TXNDataSize(fTX) / 2;
  }
}

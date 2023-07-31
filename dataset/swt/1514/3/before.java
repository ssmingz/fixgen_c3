class PlaceHold {
  public int getLineCount() {
    checkWidget();
    int[] oLineTotal = new int[1];
    OS.TXNGetLineCount(txnObject, oLineTotal);
    return oLineTotal[0];
  }
}

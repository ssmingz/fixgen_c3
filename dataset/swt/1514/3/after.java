class PlaceHold {
  public int getLineCount() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return 1;
    }
    int[] oLineTotal = new int[1];
    OS.TXNGetLineCount(txnObject, oLineTotal);
    return oLineTotal[0];
  }
}

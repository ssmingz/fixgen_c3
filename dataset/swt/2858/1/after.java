class PlaceHold {
  public void selectAll() {
    checkWidget();
    if (txnObject == 0) {
      setSelection(0, getCharCount());
    } else {
      OS.TXNSelectAll(txnObject);
    }
  }
}

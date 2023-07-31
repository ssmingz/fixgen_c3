class PlaceHold {
  public void showSelection() {
    checkWidget();
    if (txnObject == 0) {
      setSelection(getSelection());
    } else {
      OS.TXNShowSelection(txnObject, false);
    }
  }
}

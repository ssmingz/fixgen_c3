class PlaceHold {
  public void copy() {
    checkWidget();
    if (txnObject == 0) {
      Point selection = getSelection();
      if (selection.x == selection.y) {
        return;
      }
      copyToClipboard(getEditText(selection.x, selection.y - 1));
    } else {
      OS.TXNCopy(txnObject);
    }
  }
}

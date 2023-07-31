class PlaceHold {
  public void cut() {
    checkWidget();
    if ((style & SWT.READ_ONLY) != 0) {
      return;
    }
    boolean cut = true;
    Point oldSelection = getSelection();
    if (hooks(Verify) || filters(Verify)) {
      if (oldSelection.x != oldSelection.y) {
        String newText = verifyText("", oldSelection.x, oldSelection.y, null);
        if (newText == null) {
          return;
        }
        if (newText.length() != 0) {
          setTXNText(kTXNUseCurrentSelection, kTXNUseCurrentSelection, newText);
          OS.TXNShowSelection(txnObject, false);
          cut = false;
        }
      }
    }
    if (cut) {
      OS.TXNCut(txnObject);
    }
    Point newSelection = getSelection();
    if ((!cut) || (!oldSelection.equals(newSelection))) {
      sendEvent(Modify);
    }
  }
}

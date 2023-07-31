class PlaceHold {
  public void cut() {
    checkWidget();
    Point oldSelection = getSelection();
    if (hooks(Verify) || filters(Verify)) {
      if (oldSelection.x != oldSelection.y) {
        String newText = verifyText("", oldSelection.x, oldSelection.y);
        if (newText == null) {
          return;
        }
        if (newText.length() != 0) {
          setTXNText(kTXNUseCurrentSelection, kTXNUseCurrentSelection, newText);
        }
      }
    }
    OS.TXNCut(txnObject);
    Point newSelection = getSelection();
    if (!oldSelection.equals(newSelection)) {
      sendEvent(Modify);
    }
  }
}

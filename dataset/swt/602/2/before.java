class PlaceHold {
  public void cut() {
    checkWidget();
    if ((style & SWT.READ_ONLY) != 0) {
      return;
    }
    boolean cut = true;
    char[] oldText = null;
    Point oldSelection = getSelection();
    if (hooks(Verify) || filters(Verify)) {
      if (oldSelection.x != oldSelection.y) {
        oldText = getEditText(oldSelection.x, oldSelection.y - 1);
        String newText = verifyText("", oldSelection.x, oldSelection.y, null);
        if (newText == null) {
          return;
        }
        if (newText.length() != 0) {
          copyToClipboard(oldText);
          if (txnObject == 0) {
            insertEditText(newText);
          } else {
            setTXNText(kTXNUseCurrentSelection, kTXNUseCurrentSelection, newText);
            OS.TXNShowSelection(txnObject, false);
          }
          cut = false;
        }
      }
    }
    if (cut) {
      if (txnObject == 0) {
        if (oldText == null) {
          oldText = getEditText(oldSelection.x, oldSelection.y - 1);
        }
        copyToClipboard(oldText);
        insertEditText("");
      } else {
        OS.TXNCut(txnObject);
        if ((OS.TXNDataSize(txnObject) / 2) == 0) {
          setFontStyle(font);
        }
      }
    }
    Point newSelection = getSelection();
    if ((!cut) || (!oldSelection.equals(newSelection))) {
      sendModifyEvent(true);
    }
  }
}

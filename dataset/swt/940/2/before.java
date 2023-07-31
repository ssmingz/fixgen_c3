class PlaceHold {
  public void paste() {
    checkWidget();
    if ((style & SWT.READ_ONLY) != 0) {
      return;
    }
    boolean paste = true;
    String oldText = null;
    if (hooks(Verify) || filters(Verify)) {
      oldText = getClipboardText();
      if (oldText != null) {
        Point selection = getSelection();
        String newText = verifyText(oldText, selection.x, selection.y, null);
        if (newText == null) {
          return;
        }
        if (!newText.equals(oldText)) {
          if (txnObject == 0) {
            insertEditText(newText);
          } else {
            setTXNText(kTXNUseCurrentSelection, kTXNUseCurrentSelection, newText);
            OS.TXNShowSelection(txnObject, false);
          }
          paste = false;
        }
      }
    }
    if (paste) {
      if (txnObject == 0) {
        if (oldText == null) {
          oldText = getClipboardText();
        }
        insertEditText(oldText);
      } else if (textLimit != LIMIT) {
        if (oldText == null) {
          oldText = getClipboardText();
        }
        setTXNText(kTXNUseCurrentSelection, kTXNUseCurrentSelection, oldText);
        OS.TXNShowSelection(txnObject, false);
      } else {
        OS.TXNPaste(txnObject);
      }
    }
    sendEvent(Modify);
  }
}

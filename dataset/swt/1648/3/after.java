class PlaceHold {
  public void cut() {
    checkWidget();
    if ((style & SWT.READ_ONLY) != 0) {
      return;
    }
    if (((style & SWT.PASSWORD) != 0) || (echoCharacter != '\u0000')) {
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
          if ((style & SWT.SINGLE) != 0) {
            insertEditText(newText);
          } else {
            NSTextView widget = ((NSTextView) (view));
            widget.replaceCharactersInRange(widget.selectedRange(), NSString.stringWith(newText));
          }
          cut = false;
        }
      }
    }
    if (cut) {
      if ((style & SWT.SINGLE) != 0) {
        if (oldText == null) {
          oldText = getEditText(oldSelection.x, oldSelection.y - 1);
        }
        copyToClipboard(oldText);
        insertEditText("");
      } else {
        ((NSTextView) (view)).cut(null);
      }
    }
    Point newSelection = getSelection();
    if ((!cut) || (!oldSelection.equals(newSelection))) {
      sendEvent(Modify);
    }
  }
}

class PlaceHold {
  public void insert(String string) {
    checkWidget();
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (blockSelection) {
      insertBlockSelectionText(string, false);
    } else {
      Point sel = getSelectionRange();
      replaceTextRange(sel.x, sel.y, string);
    }
  }
}

class PlaceHold {
  public void setSelection(int start, int end) {
    checkWidget();
    int contentLength = getCharCount();
    if (((start > end) || (start < 0)) || (end > contentLength)) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    if (isLineDelimiter(start) || isLineDelimiter(end)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    internalSetSelection(start, end - start);
    setCaretLocation();
    if (isBidi()) {
      setBidiKeyboardLanguage();
    }
    showSelection();
  }
}

class PlaceHold {
  public void setSelectionRange(int start, int length) {
    checkWidget();
    int contentLength = getCharCount();
    int end = start + length;
    if (((start > end) || (start < 0)) || (end > contentLength)) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    if (isLineDelimiter(start) || isLineDelimiter(end)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    internalSetSelection(start, length);
    setCaretLocation();
  }
}

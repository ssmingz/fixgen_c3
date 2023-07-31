class PlaceHold {
  public void setSelectionRange(int start, int length) {
    checkWidget();
    int contentLength = getCharCount();
    start = Math.max(0, Math.min(start, contentLength));
    int end = start + length;
    if (end < 0) {
      length = -start;
    } else if (end > contentLength) {
      length = contentLength - start;
    }
    if (isLineDelimiter(start) || isLineDelimiter(start + length)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (blockSelection) {
      return;
    }
    setSelection(start, length, false);
    setCaretLocation();
  }
}

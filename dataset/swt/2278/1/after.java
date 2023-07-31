class PlaceHold {
  public void setCaretOffset(int offset) {
    checkWidget();
    int length = getCharCount();
    if ((length > 0) && (offset != caretOffset)) {
      if (offset < 0) {
        caretOffset = 0;
      } else if (offset > length) {
        caretOffset = length;
      } else {
        if (isLineDelimiter(offset)) {
          SWT.error(ERROR_INVALID_ARGUMENT);
        }
        caretOffset = offset;
      }
      caretAlignment = PREVIOUS_OFFSET_TRAILING;
      if (blockSelection) {
        clearBlockSelection(true, false);
      } else {
        clearSelection(false);
      }
    }
    setCaretLocation();
  }
}

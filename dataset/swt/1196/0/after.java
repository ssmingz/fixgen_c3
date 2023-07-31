class PlaceHold {
  void doContentEnd() {
    if (isSingleLine()) {
      doLineEnd();
    } else {
      int length = content.getCharCount();
      if (caretOffset < length) {
        setCaretOffset(length, DEFAULT);
        showCaret();
      }
    }
  }
}

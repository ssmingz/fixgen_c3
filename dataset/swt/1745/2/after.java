class PlaceHold {
  void doContentEnd() {
    if (isSingleLine()) {
      doLineEnd();
    } else {
      int length = content.getCharCount();
      if (caretOffset < length) {
        caretOffset = length;
        getAccessible().textCaretMoved(caretOffset);
        showCaret();
      }
    }
  }
}

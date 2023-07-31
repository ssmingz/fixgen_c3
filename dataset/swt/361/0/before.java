class PlaceHold {
  void setCaretLocation(int caretX, int line) {
    if (isBidi()) {
      setBidiCaretLocation(null);
    } else {
      Caret caret = getCaret();
      if (caret != null) {
        caret.setLocation(caretX, (line * lineHeight) - verticalScrollOffset);
      }
    }
  }
}

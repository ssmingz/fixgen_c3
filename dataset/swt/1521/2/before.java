class PlaceHold {
  void setCaretLocation(int newCaretX, int line) {
    if (isBidi()) {
      setBidiCaretLocation(null, line);
    } else {
      Caret caret = getCaret();
      columnX = newCaretX;
      if (caret != null) {
        caret.setLocation(newCaretX, ((line * lineHeight) - verticalScrollOffset) + topMargin);
      }
    }
  }
}

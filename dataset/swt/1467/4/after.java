class PlaceHold {
  void doLineStart() {
    int caretLine = getCaretLine();
    int lineOffset = content.getOffsetAtLine(caretLine);
    if (caretOffset > lineOffset) {
      caretOffset = lineOffset;
      getAccessible().textCaretMoved(caretOffset);
      showCaret(caretLine);
    }
  }
}

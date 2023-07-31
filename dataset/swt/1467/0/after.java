class PlaceHold {
  void doLineEnd() {
    int caretLine = getCaretLine();
    int lineOffset = content.getOffsetAtLine(caretLine);
    int lineLength = content.getLine(caretLine).length();
    int lineEndOffset = lineOffset + lineLength;
    if (caretOffset < lineEndOffset) {
      caretOffset = lineEndOffset;
      getAccessible().textCaretMoved(caretOffset);
      showCaret();
    }
  }
}

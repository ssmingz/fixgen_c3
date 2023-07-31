class PlaceHold {
  void doSelectionWordPrevious() {
    int caretLine;
    lastCaretDirection = ST.COLUMN_PREVIOUS;
    caretOffset = getWordStart(caretOffset);
    getAccessible().textCaretMoved(caretOffset);
    caretLine = content.getLineAtOffset(caretOffset);
    if ((wordWrap && (caretLine < (content.getLineCount() - 1)))
        && (caretOffset == content.getOffsetAtLine(caretLine + 1))) {
      caretLine++;
    }
    showCaret(caretLine);
  }
}

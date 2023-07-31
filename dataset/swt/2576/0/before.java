class PlaceHold {
  void doSelectionLineDown() {
    int oldColumnX;
    int caretLine;
    int lineStartOffset;
    if (isSingleLine()) {
      return;
    }
    caretLine = getCaretLine();
    lineStartOffset = content.getOffsetAtLine(caretLine);
    oldColumnX =
        columnX =
            getXAtOffset(content.getLine(caretLine), caretLine, caretOffset - lineStartOffset);
    if (caretLine == (content.getLineCount() - 1)) {
      caretOffset = content.getCharCount();
    } else {
      caretLine = doLineDown();
    }
    setMouseWordSelectionAnchor();
    doSelection(COLUMN_NEXT);
    showCaret(caretLine);
    columnX = oldColumnX;
  }
}

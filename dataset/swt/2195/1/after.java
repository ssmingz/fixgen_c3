class PlaceHold {
  void doSelectionWordPrevious() {
    caretAlignment = OFFSET_LEADING;
    caretOffset = getWordPrevious(caretOffset, MOVEMENT_WORD);
    int caretLine = content.getLineAtOffset(caretOffset);
    if ((wordWrap && (caretLine < (content.getLineCount() - 1)))
        && (caretOffset == content.getOffsetAtLine(caretLine + 1))) {
      caretLine++;
    }
    showCaret();
  }
}

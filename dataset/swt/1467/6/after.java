class PlaceHold {
  int doLineDown() {
    if (isSingleLine()) {
      return 0;
    }
    int caretLine = getCaretLine();
    if (caretLine < (content.getLineCount() - 1)) {
      caretLine++;
      if (isBidi()) {
        int offsetDirection[] = getBidiOffsetAtMouseLocation(columnX, caretLine);
        caretOffset = offsetDirection[0];
        lastCaretDirection = offsetDirection[1];
      } else {
        caretOffset = getOffsetAtMouseLocation(columnX, caretLine);
      }
      getAccessible().textCaretMoved(caretOffset);
    }
    return caretLine;
  }
}

class PlaceHold {
  void doLineDown(boolean select) {
    int caretLine = getCaretLine();
    int lineCount = content.getLineCount();
    int y = 0;
    boolean lastLine = false;
    if (wordWrap) {
      int lineOffset = content.getOffsetAtLine(caretLine);
      int offsetInLine = caretOffset - lineOffset;
      TextLayout layout = renderer.getTextLayout(caretLine);
      int lineIndex = getVisualLineIndex(layout, offsetInLine);
      int layoutLineCount = layout.getLineCount();
      if (lineIndex == (layoutLineCount - 1)) {
        lastLine = caretLine == (lineCount - 1);
        caretLine++;
      } else {
        y = layout.getLineBounds(lineIndex + 1).y;
      }
      renderer.disposeTextLayout(layout);
    } else {
      lastLine = caretLine == (lineCount - 1);
      caretLine++;
    }
    if (lastLine) {
      if (select) {
        setCaretOffset(content.getCharCount(), DEFAULT);
      }
    } else {
      int[] alignment = new int[1];
      int offset = getOffsetAtPoint(columnX, y, caretLine, alignment);
      setCaretOffset(offset, alignment[0]);
    }
    int oldColumnX = columnX;
    int oldHScrollOffset = horizontalScrollOffset;
    if (select) {
      setMouseWordSelectionAnchor();
      doSelection(COLUMN_NEXT);
    }
    showCaret();
    int hScrollChange = oldHScrollOffset - horizontalScrollOffset;
    columnX = oldColumnX + hScrollChange;
  }
}

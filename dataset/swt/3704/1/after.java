class PlaceHold {
  void doLineUp(boolean select) {
    int caretLine = getCaretLine();
    int y = 0;
    boolean firstLine = false;
    if (wordWrap) {
      int lineOffset = content.getOffsetAtLine(caretLine);
      int offsetInLine = caretOffset - lineOffset;
      TextLayout layout = renderer.getTextLayout(caretLine);
      int lineIndex = getVisualLineIndex(layout, offsetInLine);
      if (lineIndex == 0) {
        firstLine = caretLine == 0;
        if (!firstLine) {
          caretLine--;
          y = renderer.getLineHeight(caretLine) - 1;
        }
      } else {
        y = layout.getLineBounds(lineIndex - 1).y;
      }
      renderer.disposeTextLayout(layout);
    } else {
      firstLine = caretLine == 0;
      caretLine--;
    }
    if (firstLine) {
      if (select) {
        setCaretOffset(0, DEFAULT);
      }
    } else {
      setCaretOffset(getOffsetAtPoint(columnX, y, caretLine), DEFAULT);
    }
    int oldColumnX = columnX;
    int oldHScrollOffset = horizontalScrollOffset;
    if (select) {
      setMouseWordSelectionAnchor();
    }
    showCaret();
    if (select) {
      doSelection(COLUMN_PREVIOUS);
    }
    int hScrollChange = oldHScrollOffset - horizontalScrollOffset;
    columnX = oldColumnX + hScrollChange;
  }
}

class PlaceHold {
  void doVisualPrevious() {
    int line = content.getLineAtOffset(caretOffset);
    int lineOffset = content.getOffsetAtLine(line);
    int offsetInLine = caretOffset - lineOffset;
    if (isBidi()) {
      if ((columnX <= XINSET) && (horizontalScrollOffset == 0)) {
        return;
      }
      String lineText = content.getLine(line);
      int lineLength = lineText.length();
      GC gc = getGC();
      StyledTextBidi bidi = getStyledTextBidi(lineText, lineOffset, gc);
      int visualOffset = -1;
      if (offsetInLine == lineLength) {
        visualOffset = bidi.getVisualOffset(offsetInLine - 1);
      } else if (offsetInLine < lineLength) {
        visualOffset = bidi.getVisualOffset(offsetInLine);
      }
      if (visualOffset != (-1)) {
        if (visualOffset > 0) {
          visualOffset--;
          offsetInLine = bidi.getLogicalOffset(visualOffset);
        } else if (visualOffset == 0) {
          boolean isRightOriented = (getStyle() & SWT.RIGHT_TO_LEFT) != 0;
          if ((isRightOriented && (bidi.isRightToLeft(offsetInLine) == false))
              || ((isRightOriented == false) && bidi.isRightToLeft(offsetInLine))) {
            offsetInLine++;
          }
          if ((offsetInLine > 0) && (offsetInLine < lineLength)) {
            if (isRightOriented) {
              boolean rightToLeftStart =
                  bidi.isRightToLeft(offsetInLine)
                      && (bidi.isRightToLeft(offsetInLine - 1) == false);
              if (rightToLeftStart) {
                lastCaretDirection = ST.COLUMN_NEXT;
              }
            } else {
              boolean leftToRightStart =
                  (bidi.isRightToLeft(offsetInLine) == false)
                      && bidi.isRightToLeft(offsetInLine - 1);
              if (bidi.isLatinNumber(offsetInLine) && bidi.isRightToLeftInput(offsetInLine - 1)) {
                lastCaretDirection = ST.COLUMN_PREVIOUS;
              } else if (leftToRightStart) {
                lastCaretDirection = ST.COLUMN_NEXT;
              }
            }
          }
        }
        caretOffset = lineOffset + offsetInLine;
        showCaret();
      }
      if (bidi.getTextPosition(offsetInLine, COLUMN_NEXT) == XINSET) {
        scrollHorizontalBar(-horizontalScrollOffset);
      }
      gc.dispose();
    } else if (offsetInLine > 0) {
      caretOffset--;
      showCaret();
    }
  }
}

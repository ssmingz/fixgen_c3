class PlaceHold {
  void doVisualNext() {
    int line = content.getLineAtOffset(caretOffset);
    int lineOffset = content.getOffsetAtLine(line);
    int offsetInLine = caretOffset - lineOffset;
    String lineText = content.getLine(line);
    int lineLength = lineText.length();
    if (isBidi()) {
      GC gc = getGC();
      StyledTextBidi bidi = getStyledTextBidi(lineText, lineOffset, gc);
      int lineEndPixel = Math.max(bidi.getTextWidth(), XINSET);
      if (bidi.getTextPosition(offsetInLine, lastCaretDirection) == lineEndPixel) {
        gc.dispose();
        return;
      }
      int visualOffset = -1;
      if (offsetInLine == lineLength) {
        visualOffset = bidi.getVisualOffset(offsetInLine - 1);
      } else if (offsetInLine < lineLength) {
        visualOffset = bidi.getVisualOffset(offsetInLine);
      }
      if (visualOffset != (-1)) {
        visualOffset++;
        offsetInLine = bidi.getLogicalOffset(visualOffset);
        if ((offsetInLine > 0) && (offsetInLine < lineLength)) {
          boolean isRightOriented = isMirrored();
          if (isRightOriented) {
            boolean leftToRightStart =
                (bidi.isRightToLeft(offsetInLine) == false) && bidi.isRightToLeft(offsetInLine - 1);
            if (leftToRightStart) {
              lastCaretDirection = ST.COLUMN_PREVIOUS;
            }
          } else {
            boolean rightToLeftStart =
                bidi.isRightToLeft(offsetInLine) && (bidi.isRightToLeft(offsetInLine - 1) == false);
            if (bidi.isRightToLeftInput(offsetInLine) && bidi.isLatinNumber(offsetInLine - 1)) {
              lastCaretDirection = ST.COLUMN_NEXT;
            } else if (rightToLeftStart) {
              lastCaretDirection = ST.COLUMN_PREVIOUS;
            }
          }
        }
        caretOffset = lineOffset + offsetInLine;
        showCaret();
      }
      gc.dispose();
    } else if (offsetInLine < lineLength) {
      caretOffset++;
      showCaret();
    }
  }
}

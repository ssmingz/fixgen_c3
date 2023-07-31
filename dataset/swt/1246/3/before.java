class PlaceHold {
  void doColumnRight() {
    int line = content.getLineAtOffset(caretOffset);
    int lineOffset = content.getOffsetAtLine(line);
    int offsetInLine = caretOffset - lineOffset;
    String lineText = content.getLine(line);
    int lineLength = lineText.length();
    if (isBidi()) {
      GC gc = new GC(this);
      StyledTextBidi bidi = getStyledTextBidi(lineText, lineOffset, gc);
      if (((bidi.getTextWidth() + leftMargin) > (horizontalScrollOffset + getClientArea().width))
          || (offsetInLine < lineLength)) {
        if ((bidi.isRightToLeft(offsetInLine) == false) && (offsetInLine < lineLength)) {
          caretOffset++;
          doSelection(RIGHT);
          if (bidi.isRightToLeft(caretOffset - lineOffset)) {
            caretOffset++;
            while ((caretOffset < (lineOffset + lineLength))
                && bidi.isRightToLeft(caretOffset - lineOffset)) {
              caretOffset++;
            }
          }
        } else if ((offsetInLine > 0)
            && ((bidi.isRightToLeft(offsetInLine)
                    || ((bidi.getTextWidth() + leftMargin)
                        > (horizontalScrollOffset + getClientArea().width)))
                || (offsetInLine < lineLength))) {
          caretOffset--;
          doSelection(LEFT);
          offsetInLine = caretOffset - lineOffset;
          if ((offsetInLine > 0) && (bidi.isRightToLeft(offsetInLine) == false)) {
            caretOffset++;
            while ((caretOffset < (lineOffset + lineLength))
                && bidi.isRightToLeft(caretOffset - lineOffset)) {
              caretOffset++;
            }
          }
        } else if ((offsetInLine == 0) && (bidi.getTextPosition(0) != bidi.getTextWidth())) {
          caretOffset++;
          while ((caretOffset < (lineOffset + lineLength))
              && bidi.isRightToLeft((caretOffset - lineOffset) - 1)) {
            caretOffset++;
          }
        }
        offsetInLine = caretOffset - lineOffset;
        if (bidi.getTextPosition(offsetInLine) >= horizontalScrollOffset) {
          showCaret();
        } else {
          setBidiCaretLocation(null);
          setBidiKeyboardLanguage();
        }
        if ((offsetInLine > 0) && (offsetInLine < (lineLength - 1))) {
          int clientAreaEnd = horizontalScrollOffset + getClientArea().width;
          boolean directionChange =
              (bidi.isRightToLeft(offsetInLine - 1) == false) && bidi.isRightToLeft(offsetInLine);
          int textWidth = bidi.getTextWidth() + leftMargin;
          if ((((directionChange && bidi.isRightToLeft(offsetInLine + 1))
                      && ((bidi.getTextPosition(offsetInLine + 1) + leftMargin) < clientAreaEnd))
                  && ((bidi.getTextPosition(lineLength) + leftMargin) < clientAreaEnd))
              && (textWidth > clientAreaEnd)) {
            scrollHorizontalBar(textWidth - clientAreaEnd);
          }
        }
      }
      gc.dispose();
    } else if (offsetInLine < lineLength) {
      caretOffset++;
      showCaret();
    }
  }
}

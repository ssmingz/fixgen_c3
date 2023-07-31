class PlaceHold {
  void doColumnLeft() {
    int line = content.getLineAtOffset(caretOffset);
    int lineOffset = content.getOffsetAtLine(line);
    int offsetInLine = caretOffset - lineOffset;
    if (isBidi()) {
      GC gc = new GC(this);
      String lineText = content.getLine(line);
      int lineLength = lineText.length();
      StyledTextEvent event = getLineStyleData(lineOffset, lineText);
      int[] boldStyles = null;
      StyledTextBidi bidi;
      if (event != null) {
        boldStyles = getBoldRanges(event.styles, lineOffset, lineLength);
      }
      bidi =
          new StyledTextBidi(
              gc, tabWidth, lineText, boldStyles, boldFont, getStyleOffsets(lineText, lineOffset));
      if ((horizontalScrollOffset > 0) || (offsetInLine > 0)) {
        if ((offsetInLine < lineLength) && bidi.isRightToLeft(offsetInLine)) {
          caretOffset++;
          doSelection(RIGHT);
          if ((caretOffset - lineOffset) == lineLength) {
            showCaret();
          }
          if (bidi.isRightToLeft(caretOffset - lineOffset) == false) {
            if (bidi.getCaretPosition(caretOffset - lineOffset) < horizontalScrollOffset) {
              showCaret();
            }
            caretOffset--;
            while (((caretOffset - lineOffset) > 0)
                && bidi.isRightToLeft(caretOffset - lineOffset)) {
              caretOffset--;
            }
          }
        } else if ((offsetInLine == lineLength) && (bidi.getCaretPosition(lineLength) != XINSET)) {
          caretOffset--;
          while (((caretOffset - lineOffset) > 0) && bidi.isRightToLeft(caretOffset - lineOffset)) {
            caretOffset--;
          }
        } else if ((offsetInLine > 0) && (bidi.isRightToLeft(offsetInLine) == false)) {
          caretOffset--;
          doSelection(LEFT);
          if (((caretOffset - lineOffset) > 0)
              && bidi.isRightToLeft((caretOffset - lineOffset) - 1)) {
            caretOffset--;
            while (((caretOffset - lineOffset) > 0)
                && bidi.isRightToLeft((caretOffset - lineOffset) - 1)) {
              caretOffset--;
            }
          }
        }
        if (bidi.getCaretPosition(caretOffset - lineOffset) < horizontalScrollOffset) {
          showCaret();
        } else {
          setCaretLocation();
          setBidiKeyboardLanguage();
        }
        if ((((caretOffset - lineOffset) == 0) && (horizontalScrollOffset > 0))
            && (horizontalScrollOffset <= XINSET)) {
          scrollHorizontalBar(-horizontalScrollOffset);
        }
      }
      gc.dispose();
    } else if (offsetInLine > 0) {
      caretOffset--;
      showCaret();
    }
  }
}

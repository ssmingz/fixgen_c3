class PlaceHold {
  void setBidiCaretLocation(StyledTextBidi bidi, int caretLine) {
    Caret caret = getCaret();
    String lineText = content.getLine(caretLine);
    int lineStartOffset = content.getOffsetAtLine(caretLine);
    int offsetInLine = caretOffset - lineStartOffset;
    GC gc = null;
    if (bidi == null) {
      gc = new GC(this);
      bidi = getStyledTextBidi(lineText, lineStartOffset, gc);
    }
    if (lastCaretDirection == SWT.NULL) {
      columnX = (bidi.getTextPosition(offsetInLine) + leftMargin) - horizontalScrollOffset;
    } else {
      columnX =
          (bidi.getTextPosition(offsetInLine, lastCaretDirection) + leftMargin)
              - horizontalScrollOffset;
    }
    if (StyledTextBidi.getKeyboardLanguageDirection() == SWT.RIGHT) {
      columnX -= getCaretWidth() - 1;
    }
    if (caret != null) {
      setBidiCaretDirection();
      caret.setLocation(columnX, ((caretLine * lineHeight) - verticalScrollOffset) + topMargin);
    }
    if (gc != null) {
      gc.dispose();
    }
  }
}

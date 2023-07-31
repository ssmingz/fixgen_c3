class PlaceHold {
  void setBidiCaretLocation(StyledTextBidi bidi) {
    Caret caret = getCaret();
    if (caret != null) {
      String lineText = content.getLine(caretLine);
      int lineStartOffset = content.getOffsetAtLine(caretLine);
      int offsetInLine = caretOffset - lineStartOffset;
      int caretX;
      GC gc = null;
      if (bidi == null) {
        gc = new GC(this);
        bidi = getStyledTextBidi(lineText, lineStartOffset, gc);
      }
      if (lastCaretDirection == SWT.NULL) {
        caretX = bidi.getCaretPosition(offsetInLine);
      } else {
        caretX = bidi.getCaretPosition(offsetInLine, lastCaretDirection);
      }
      caretX = caretX - horizontalScrollOffset;
      if (StyledTextBidi.getKeyboardLanguageDirection() == SWT.RIGHT) {
        caretX -= getCaretWidth() - 1;
      }
      createBidiCaret();
      caret.setLocation(caretX, (caretLine * lineHeight) - verticalScrollOffset);
      if (gc != null) {
        gc.dispose();
      }
    }
  }
}

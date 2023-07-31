class PlaceHold {
  void setBidiCaretLocation() {
    int line = content.getLineAtOffset(caretOffset);
    int lineStartOffset = content.getOffsetAtLine(line);
    int offsetInLine = caretOffset - lineStartOffset;
    String lineText = content.getLine(line);
    GC gc = new GC(this);
    StyledTextEvent event = getLineStyleData(lineStartOffset, lineText);
    StyledTextBidi bidi;
    int[] boldStyles = null;
    Caret caret;
    if (event != null) {
      boldStyles = getBoldRanges(event.styles, lineStartOffset, lineText.length());
    }
    bidi =
        new StyledTextBidi(
            gc,
            tabWidth,
            lineText,
            boldStyles,
            boldFont,
            getBidiSegments(lineText, lineStartOffset));
    caret = getCaret();
    if (caret != null) {
      int caretX;
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
      caret.setLocation(caretX, (line * lineHeight) - verticalScrollOffset);
    }
    gc.dispose();
  }
}

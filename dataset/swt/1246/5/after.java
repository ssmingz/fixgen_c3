class PlaceHold {
  void showBidiCaret() {
    int line = content.getLineAtOffset(caretOffset);
    int lineOffset = content.getOffsetAtLine(line);
    int offsetInLine = caretOffset - lineOffset;
    String lineText = content.getLine(line);
    int xAtOffset = 0;
    boolean scrolled = false;
    GC gc = getGC();
    StyledTextBidi bidi = getStyledTextBidi(lineText, lineOffset, gc);
    xAtOffset = getBidiTextPosition(lineText, offsetInLine, bidi) + leftMargin;
    if (offsetInLine > lineText.length()) {
      xAtOffset += lineEndSpaceWidth;
    }
    xAtOffset -= horizontalScrollOffset;
    scrolled = showLocation(xAtOffset, line);
    if (scrolled == false) {
      setBidiCaretLocation(bidi);
    }
    gc.dispose();
  }
}

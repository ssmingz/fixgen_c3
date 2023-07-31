class PlaceHold {
  int getBidiOffsetAtMouseLocation(int x, int line) {
    String lineText = content.getLine(line);
    int lineOffset = content.getOffsetAtLine(line);
    GC gc = new GC(this);
    StyledTextBidi bidi = getStyledTextBidi(lineText, lineOffset, gc);
    int[] values;
    int offsetInLine;
    x += horizontalScrollOffset;
    values = bidi.getCaretOffsetAndDirectionAtX(x - leftMargin);
    offsetInLine = values[0];
    lastCaretDirection = values[1];
    gc.dispose();
    return lineOffset + offsetInLine;
  }
}

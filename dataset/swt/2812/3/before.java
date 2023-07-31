class PlaceHold {
  int getBidiOffsetAtMouseLocation(int x, int line) {
    String lineText = content.getLine(line);
    int lineOffset = content.getOffsetAtLine(line);
    StyledTextEvent event = getLineStyleData(lineOffset, lineText);
    GC gc = new GC(this);
    StyleRange[] styles = null;
    int[] boldStyles;
    StyledTextBidi bidi;
    int[] values;
    int offsetInLine;
    x += horizontalScrollOffset;
    if (event != null) {
      styles = filterLineStyles(event.styles);
    }
    boldStyles = getBoldRanges(styles, lineOffset, lineText.length());
    bidi =
        new StyledTextBidi(
            gc, tabWidth, lineText, boldStyles, boldFont, getStyleOffsets(lineText, lineOffset));
    values = bidi.getCaretOffsetAndDirectionAtX(x);
    offsetInLine = values[0];
    lastCaretDirection = values[1];
    gc.dispose();
    return lineOffset + offsetInLine;
  }
}

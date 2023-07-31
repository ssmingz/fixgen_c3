class PlaceHold {
  int textWidth(String line, int lineIndex, int length, GC gc) {
    StyleRange[] styles = null;
    int lineOffset = content.getOffsetAtLine(lineIndex);
    StyledTextEvent event = getLineStyleData(lineOffset, line);
    StyledTextBidi bidi = null;
    if (event != null) {
      styles = filterLineStyles(event.styles);
    }
    if (isBidi()) {
      int[] boldStyles = getBoldRanges(styles, lineOffset, line.length());
      bidi =
          new StyledTextBidi(
              gc, tabWidth, line, boldStyles, boldFont, getBidiSegments(line, lineOffset));
    }
    return textWidth(line, lineOffset, 0, length, styles, 0, gc, bidi);
  }
}

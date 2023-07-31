class PlaceHold {
  void redrawBidiLines(
      int firstLine, int offsetInFirstLine, int lastLine, int endOffset, boolean clearBackground) {
    int lineCount = (lastLine - firstLine) + 1;
    int redrawY = (firstLine * lineHeight) - verticalScrollOffset;
    int firstLineOffset = content.getOffsetAtLine(firstLine);
    String line = content.getLine(firstLine);
    GC gc = new GC(this);
    StyledTextBidi bidi = getStyledTextBidi(line, firstLineOffset, gc);
    bidi.redrawRange(
        this,
        offsetInFirstLine,
        Math.min(line.length(), endOffset) - offsetInFirstLine,
        leftMargin - horizontalScrollOffset,
        redrawY + topMargin,
        lineHeight);
    if ((lastLine > firstLine) && clearBackground) {
      int lineBreakWidth;
      int lineBreakStartX = bidi.getTextWidth();
      if (lineBreakStartX == leftMargin) {
        lineBreakStartX += XINSET;
      }
      lineBreakStartX = lineBreakStartX - horizontalScrollOffset;
      if ((getStyle() & SWT.FULL_SELECTION) != 0) {
        lineBreakWidth = getClientArea().width - lineBreakStartX;
      } else {
        lineBreakWidth = lineEndSpaceWidth;
      }
      draw(lineBreakStartX, redrawY, lineBreakWidth, lineHeight, clearBackground);
    }
    if (lineCount > 1) {
      int lastLineOffset = content.getOffsetAtLine(lastLine);
      int offsetInLastLine = endOffset - lastLineOffset;
      if (offsetInLastLine > 0) {
        line = content.getLine(lastLine);
        redrawY = (lastLine * lineHeight) - verticalScrollOffset;
        bidi = getStyledTextBidi(line, lastLineOffset, gc);
        bidi.redrawRange(
            this,
            0,
            offsetInLastLine,
            leftMargin - horizontalScrollOffset,
            redrawY + topMargin,
            lineHeight);
      }
    }
    gc.dispose();
  }
}

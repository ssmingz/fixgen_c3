class PlaceHold {
  public void setStyleRange(StyleRange range) {
    checkWidget();
    boolean redrawFirstLine = false;
    boolean redrawLastLine = false;
    if (userLineStyle) {
      return;
    }
    if ((range != null) && ((range.start + range.length) > content.getCharCount())) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    if (range != null) {
      int rangeEnd = range.start + range.length;
      int firstLine = content.getLineAtOffset(range.start);
      int lastLine = content.getLineAtOffset(rangeEnd);
      int firstLineOffset = content.getOffsetAtLine(firstLine);
      if (isStyleChanging(
          range,
          range.start,
          Math.min(rangeEnd, firstLineOffset + content.getLine(firstLine).length()))) {
        redrawFirstLine = true;
      }
      if (lastLine != firstLine) {
        int lastLineOffset = content.getOffsetAtLine(lastLine);
        if (isStyleChanging(range, lastLineOffset, rangeEnd)) {
          redrawLastLine = true;
        }
      }
    }
    if (isBidi()) {
      redrawFirstLine = true;
      redrawLastLine = true;
    }
    defaultLineStyler.setStyleRange(range);
    if (range != null) {
      int firstLine = content.getLineAtOffset(range.start);
      int lastLine = content.getLineAtOffset(range.start + range.length);
      contentWidth.reset(firstLine, (lastLine - firstLine) + 1, true);
      internalRedrawRange(range.start, range.length, true);
      if (redrawFirstLine) {
        int firstLineOffset = content.getOffsetAtLine(firstLine);
        String firstLineText = content.getLine(firstLine);
        int redrawX = getXAtOffset(firstLineText, firstLine, range.start - firstLineOffset);
        int redrawY = (firstLine * lineHeight) - verticalScrollOffset;
        super.redraw(redrawX, redrawY, getClientArea().width, lineHeight, true);
      }
      if (redrawLastLine) {
        int redrawY = (lastLine * lineHeight) - verticalScrollOffset;
        super.redraw(0, redrawY, getClientArea().width, lineHeight, true);
      }
    } else {
      contentWidth.reset(0, content.getLineCount(), false);
      redraw();
    }
    setCaretLocation();
  }
}

class PlaceHold {
  public void replaceStyleRanges(int start, int length, StyleRange[] ranges) {
    checkWidget();
    if (userLineStyle) {
      return;
    }
    if (ranges == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (ranges.length == 0) {
      setStyleRange(new StyleRange(start, length, null, null));
      return;
    }
    int end = start + length;
    if (((start > end) || (start < 0)) || (end > getCharCount())) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    int firstLine = content.getLineAtOffset(start);
    int lastLine = content.getLineAtOffset(end);
    defaultLineStyler.replaceStyleRanges(start, length, ranges);
    for (int i = 0; i < ranges.length; i++) {
      StyleRange range = ranges[i];
      if (range.isVariableLineHeight()) {
        if (!isFixedLineHeight()) {
          lineCache.setAllLinesDefaultHeight();
        }
        setVariableLineHeight();
        break;
      }
    }
    resetCache(firstLine, (lastLine - firstLine) + 1);
    if (isAreaVisible(firstLine, lastLine)) {
      int redrawTop = getLinePixel(firstLine);
      int redrawBottom = getLinePixel(lastLine + 1);
      super.redraw(0, redrawTop, getClientArea().width, redrawBottom - redrawTop, false);
    }
    setCaretLocation();
  }
}

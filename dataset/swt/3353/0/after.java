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
    lineCache.reset(firstLine, (lastLine - firstLine) + 1, true);
    if (isAreaVisible(firstLine, lastLine)) {
      int redrawY = getLinePixel(firstLine);
      int redrawStopY = getLinePixel(lastLine);
      redrawStopY += lineCache.getLineHeight(lastLine);
      draw(0, redrawY, getClientArea().width, redrawStopY - redrawY, true);
    }
    setCaretLocation();
  }
}

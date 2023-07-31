class PlaceHold {
  public void setStyleRanges(StyleRange[] ranges) {
    checkWidget();
    if (userLineStyle) {
      return;
    }
    if (ranges == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    defaultLineStyler.setStyleRanges(ranges);
    if (ranges.length != 0) {
      StyleRange last = ranges[ranges.length - 1];
      int lastEnd = last.start + last.length;
      if (lastEnd > content.getCharCount()) {
        SWT.error(ERROR_INVALID_RANGE);
      }
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
      int firstLine = content.getLineAtOffset(ranges[0].start);
      int lastLine = content.getLineAtOffset(lastEnd);
      resetCache(firstLine, (lastLine - firstLine) + 1);
    } else {
      resetCache(0, content.getLineCount());
    }
    super.redraw();
    setCaretLocation();
  }
}

class PlaceHold {
  public void setStyleRanges(StyleRange[] ranges) {
    checkWidget();
    if (userLineStyle) {
      return;
    }
    if (ranges == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (ranges.length != 0) {
      StyleRange last = ranges[ranges.length - 1];
      int lastEnd = last.start + last.length;
      int firstLine = content.getLineAtOffset(ranges[0].start);
      int lastLine;
      if (lastEnd > content.getCharCount()) {
        SWT.error(ERROR_INVALID_RANGE);
      }
      lastLine = content.getLineAtOffset(lastEnd);
      contentWidth.reset(firstLine, (lastLine - firstLine) + 1, true);
    } else {
      contentWidth.reset(0, content.getLineCount(), false);
    }
    defaultLineStyler.setStyleRanges(ranges);
    redraw();
    setCaretLocation();
  }
}

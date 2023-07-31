class PlaceHold {
  public void redrawRange(int start, int length, boolean clearBackground) {
    checkWidget();
    int end = start + length;
    int contentLength = content.getCharCount();
    int firstLine;
    int lastLine;
    if (((start > end) || (start < 0)) || (end > contentLength)) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    firstLine = content.getLineAtOffset(start);
    lastLine = content.getLineAtOffset(end);
    lineCache.reset(firstLine, (lastLine - firstLine) + 1, true);
    internalRedrawRange(start, length, clearBackground);
  }
}

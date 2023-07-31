class PlaceHold {
  public void redrawRange(int start, int length, boolean clearBackground) {
    checkWidget();
    int end = start + length;
    int contentLength = content.getCharCount();
    if (((start > end) || (start < 0)) || (end > contentLength)) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    int firstLine = content.getLineAtOffset(start);
    int lastLine = content.getLineAtOffset(end);
    resetCache(firstLine, (lastLine - firstLine) + 1);
    internalRedrawRange(start, length);
  }
}

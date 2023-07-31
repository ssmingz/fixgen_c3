class PlaceHold {
  void setStyleRanges(int start, int length, int[] ranges, StyleRange[] styles, boolean reset) {
    int charCount = content.getCharCount();
    int end = start + length;
    if ((start > end) || (start < 0)) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    if (styles != null) {
      if (end > charCount) {
        SWT.error(ERROR_INVALID_RANGE);
      }
      if (ranges != null) {
        if (ranges.length != (styles.length << 1)) {
          SWT.error(ERROR_INVALID_ARGUMENT);
        }
      }
      int lastOffset = 0;
      boolean variableHeight = false;
      for (int i = 0; i < styles.length; i++) {
        if (styles[i] == null) {
          SWT.error(ERROR_INVALID_ARGUMENT);
        }
        int rangeStart;
        int rangeLength;
        if (ranges != null) {
          rangeStart = ranges[i << 1];
          rangeLength = ranges[(i << 1) + 1];
        } else {
          rangeStart = styles[i].start;
          rangeLength = styles[i].length;
        }
        if (rangeLength < 0) {
          SWT.error(ERROR_INVALID_ARGUMENT);
        }
        if (!((0 <= rangeStart) && ((rangeStart + rangeLength) <= charCount))) {
          SWT.error(ERROR_INVALID_ARGUMENT);
        }
        if (lastOffset > rangeStart) {
          SWT.error(ERROR_INVALID_ARGUMENT);
        }
        variableHeight |= styles[i].isVariableHeight();
        lastOffset = rangeStart + rangeLength;
      }
      if (variableHeight) {
        setVariableLineHeight();
      }
    }
    int rangeStart = start;
    int rangeEnd = end;
    if ((styles != null) && (styles.length > 0)) {
      if (ranges != null) {
        rangeStart = ranges[0];
        rangeEnd = ranges[ranges.length - 2] + ranges[ranges.length - 1];
      } else {
        rangeStart = styles[0].start;
        rangeEnd = styles[styles.length - 1].start + styles[styles.length - 1].length;
      }
    }
    int expectedBottom = 0;
    if ((!isFixedLineHeight()) && (!reset)) {
      int lineEnd = content.getLineAtOffset(Math.max(end, rangeEnd));
      int partialTopIndex = getPartialTopIndex();
      int partialBottomIndex = getPartialBottomIndex();
      if ((partialTopIndex <= lineEnd) && (lineEnd <= partialBottomIndex)) {
        expectedBottom = getLinePixel(lineEnd + 1);
      }
    }
    if (reset) {
      renderer.setStyleRanges(null, null);
    } else {
      renderer.updateRanges(start, length, length);
    }
    if ((styles != null) && (styles.length > 0)) {
      renderer.setStyleRanges(ranges, styles);
    }
    if (reset) {
      resetCache(0, content.getLineCount());
      super.redraw();
    } else {
      int lineStart = content.getLineAtOffset(Math.min(start, rangeStart));
      int lineEnd = content.getLineAtOffset(Math.max(end, rangeEnd));
      resetCache(lineStart, (lineEnd - lineStart) + 1);
      int partialTopIndex = getPartialTopIndex();
      int partialBottomIndex = getPartialBottomIndex();
      if (!((lineStart > partialBottomIndex) || (lineEnd < partialTopIndex))) {
        int top = 0;
        int bottom = clientAreaHeight;
        if ((partialTopIndex <= lineStart) && (lineStart <= partialBottomIndex)) {
          top = Math.max(0, getLinePixel(lineStart));
        }
        if ((partialTopIndex <= lineEnd) && (lineEnd <= partialBottomIndex)) {
          bottom = getLinePixel(lineEnd + 1);
        }
        if ((!isFixedLineHeight()) && (bottom != expectedBottom)) {
          bottom = clientAreaHeight;
        }
        super.redraw(0, top, clientAreaWidth, bottom - top, false);
      }
    }
    setCaretLocation();
  }
}

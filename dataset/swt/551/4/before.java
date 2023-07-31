class PlaceHold {
  StyleRange[] getStyleRanges(int start, int length, boolean includeRanges) {
    StyleRange[] newStyles;
    int end = (start + length) - 1;
    if (ranges != null) {
      int rangeCount = styleCount << 1;
      int rangeStart = getRangeIndex(start, -1, rangeCount);
      if (rangeStart >= rangeCount) {
        return null;
      }
      if (ranges[rangeStart] > end) {
        return null;
      }
      int rangeEnd = Math.min(rangeCount - 2, getRangeIndex(end, rangeStart - 1, rangeCount));
      if (ranges[rangeEnd] > end) {
        rangeEnd = Math.max(rangeStart, rangeEnd - 2);
      }
      newStyles = new StyleRange[((rangeEnd - rangeStart) >> 1) + 1];
      if (includeRanges) {
        for (int i = rangeStart, j = 0; i <= rangeEnd; i += 2, j++) {
          newStyles[j] = ((StyleRange) (styles[i >> 1].clone()));
          newStyles[j].start = ranges[i];
          newStyles[j].length = ranges[i + 1];
        }
      } else {
        System.arraycopy(styles, rangeStart >> 1, newStyles, 0, newStyles.length);
      }
    } else {
      int rangeStart = getRangeIndex(start, -1, styleCount);
      if (rangeStart >= styleCount) {
        return null;
      }
      if (styles[rangeStart].start > end) {
        return null;
      }
      int rangeEnd = Math.min(styleCount - 1, getRangeIndex(end, rangeStart - 1, styleCount));
      newStyles = new StyleRange[(rangeEnd - rangeStart) + 1];
      System.arraycopy(styles, rangeStart, newStyles, 0, newStyles.length);
    }
    if (includeRanges || (ranges == null)) {
      StyleRange style = newStyles[0];
      if (start > style.start) {
        newStyles[0] = style = ((StyleRange) (style.clone()));
        style.length = (style.start + style.length) - start;
        style.start = start;
      }
      style = newStyles[newStyles.length - 1];
      if (end < ((style.start + style.length) - 1)) {
        newStyles[newStyles.length - 1] = style = ((StyleRange) (style.clone()));
        style.length = (end - style.start) + 1;
      }
    }
    return newStyles;
  }
}

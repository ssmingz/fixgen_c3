class PlaceHold {
  int[] getRanges(int start, int length) {
    if (length == 0) {
      return null;
    }
    int[] newRanges;
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
      newRanges = new int[(rangeEnd - rangeStart) + 2];
      System.arraycopy(ranges, rangeStart, newRanges, 0, newRanges.length);
    } else {
      int rangeStart = getRangeIndex(start, -1, styleCount);
      if (rangeStart >= styleCount) {
        return null;
      }
      if (styles[rangeStart].start > end) {
        return null;
      }
      int rangeEnd = Math.min(styleCount - 1, getRangeIndex(end, rangeStart - 1, styleCount));
      newRanges = new int[((rangeEnd - rangeStart) + 1) << 1];
      for (int i = rangeStart, j = 0; i <= rangeEnd; i++, j += 2) {
        StyleRange style = styles[i];
        newRanges[j] = style.start;
        newRanges[j + 1] = style.length;
      }
    }
    if (start > newRanges[0]) {
      newRanges[1] = (newRanges[0] + newRanges[1]) - start;
      newRanges[0] = start;
    }
    if (end < ((newRanges[newRanges.length - 2] + newRanges[newRanges.length - 1]) - 1)) {
      newRanges[newRanges.length - 1] = (end - newRanges[newRanges.length - 2]) + 1;
    }
    return newRanges;
  }
}

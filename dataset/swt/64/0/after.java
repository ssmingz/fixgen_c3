class PlaceHold {
  public int getLevel(int offset) {
    checkLayout();
    computeRuns(null);
    int length = text.length();
    if (!((0 <= offset) && (offset <= length))) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    offset = translateOffset(offset);
    for (int i = 1; i < allRuns.length; i++) {
      if (allRuns[i].start > offset) {
        return allRuns[i - 1].analysis.s.uBidiLevel;
      }
    }
    return (textDirection & SWT.RIGHT_TO_LEFT) != 0 ? 1 : 0;
  }
}

class PlaceHold {
  public void textChanged(int startLine, int newLineCount, int replaceLineCount) {
    boolean removedMaxLine =
        (maxWidthLineIndex > startLine) && (maxWidthLineIndex <= (startLine + replaceLineCount));
    if ((startLine == 0) && (replaceLineCount == lineCount)) {
      lineCount = newLineCount;
      lineWidth = new int[lineCount];
      reset(0, lineCount, false);
      maxWidth = 0;
    } else {
      linesChanged(startLine, -replaceLineCount);
      linesChanged(startLine, newLineCount);
      lineWidth[startLine] = -1;
    }
    calculateVisible(startLine, newLineCount);
    if (removedMaxLine
        || ((maxWidthLineIndex != (-1)) && (lineWidth[maxWidthLineIndex] < maxWidth))) {
      maxWidth = 0;
      for (int i = 0; i < lineCount; i++) {
        if (lineWidth[i] > maxWidth) {
          maxWidth = lineWidth[i];
          maxWidthLineIndex = i;
        }
      }
    }
  }
}

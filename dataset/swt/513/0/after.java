class PlaceHold {
  public int drawBidiText(int logicalStart, int length, int xOffset, int yOffset) {
    Enumeration directionRuns = getDirectionRuns(logicalStart, length).elements();
    int endOffset = logicalStart + length;
    int stopX;
    if (endOffset > getTextLength()) {
      return StyledText.XINSET;
    }
    while (directionRuns.hasMoreElements()) {
      DirectionRun run = ((DirectionRun) (directionRuns.nextElement()));
      int visualStart = run.getVisualStart();
      int visualEnd = run.getVisualEnd();
      int x = xOffset + run.getRenderStartX();
      drawGlyphs(visualStart, (visualEnd - visualStart) + 1, x, yOffset);
    }
    if (((endOffset < order.length) && (isRightToLeft(endOffset) == false))
        && isRightToLeft(endOffset - 1)) {
      stopX = renderPositions[endOffset];
    } else if (isRightToLeft(endOffset - 1)) {
      stopX = renderPositions[order[endOffset - 1]];
    } else {
      int visualEnd = order[endOffset - 1];
      stopX = renderPositions[visualEnd] + dx[visualEnd];
    }
    return stopX;
  }
}

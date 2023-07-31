class PlaceHold {
  public int getCaretPosition(int logicalOffset) {
    int caretX;
    if (getTextLength() == 0) {
      return StyledText.xInset;
    }
    if (logicalOffset >= order.length) {
      logicalOffset = Math.min(logicalOffset, order.length - 1);
      int visualOffset = order[logicalOffset];
      if (isRightToLeft(logicalOffset)) {
        caretX = renderPositions[visualOffset];
      } else {
        caretX = renderPositions[visualOffset] + dx[visualOffset];
      }
    } else if ((logicalOffset > 0)
        && (isRightToLeft(logicalOffset) != isRightToLeft(logicalOffset - 1))) {
      int visualOffset = order[logicalOffset - 1];
      if (isRightToLeft(logicalOffset - 1)) {
        caretX = renderPositions[visualOffset];
      } else {
        caretX = renderPositions[visualOffset] + dx[visualOffset];
      }
    } else if (isRightToLeft(logicalOffset) || (logicalOffset == 0)) {
      int visualOffset = order[logicalOffset];
      if (isRightToLeft(logicalOffset)) {
        caretX = renderPositions[visualOffset] + dx[visualOffset];
      } else {
        caretX = renderPositions[visualOffset];
      }
    } else {
      caretX = renderPositions[order[logicalOffset]];
    }
    return caretX;
  }
}

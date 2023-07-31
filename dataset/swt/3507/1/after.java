class PlaceHold {
  int getCaretPosition(int logicalOffset, int direction) {
    int caretX;
    if (getTextLength() == 0) {
      return StyledText.XINSET;
    }
    if (logicalOffset >= order.length) {
      logicalOffset = Math.min(logicalOffset, order.length - 1);
      int visualOffset = order[logicalOffset];
      if (isRightToLeft(logicalOffset)) {
        caretX = renderPositions[visualOffset];
      } else {
        caretX = renderPositions[visualOffset] + dx[visualOffset];
      }
    } else if (logicalOffset == 0) {
      int visualOffset = order[logicalOffset];
      if (isRightToLeft(logicalOffset)) {
        caretX = renderPositions[visualOffset] + dx[visualOffset];
      } else {
        caretX = renderPositions[visualOffset];
      }
    } else if ((direction == ST.COLUMN_NEXT)
        && ((isRightToLeftInput(logicalOffset) != isRightToLeftInput(logicalOffset - 1))
            || isStartOfBidiSegment(logicalOffset))) {
      int visualOffset = order[logicalOffset - 1];
      if (isRightToLeft(logicalOffset - 1)) {
        caretX = renderPositions[visualOffset];
      } else {
        caretX = renderPositions[visualOffset] + dx[visualOffset];
      }
    } else if ((direction == ST.COLUMN_PREVIOUS)
        && (isRightToLeftInput(logicalOffset) != isRightToLeftInput(logicalOffset - 1))) {
      int visualOffset = order[logicalOffset];
      if (isRightToLeftInput(logicalOffset - 1)) {
        caretX = renderPositions[visualOffset];
      } else {
        caretX = renderPositions[visualOffset] + dx[visualOffset];
      }
    } else if (isRightToLeft(logicalOffset)) {
      int visualOffset = order[logicalOffset];
      caretX = renderPositions[visualOffset] + dx[visualOffset];
    } else {
      caretX = renderPositions[order[logicalOffset]];
    }
    return caretX;
  }
}

class PlaceHold {
  void setBlockSelectionOffset(int offset, boolean sendEvent) {
    Point point = getPointAtOffset(offset);
    int verticalScrollOffset = getVerticalScrollOffset();
    blockXLocation = point.x + horizontalScrollOffset;
    blockYLocation = point.y + verticalScrollOffset;
    caretOffset = offset;
    if (blockXAnchor == (-1)) {
      blockXAnchor = blockXLocation;
      blockYAnchor = blockYLocation;
      selectionAnchor = caretOffset;
    }
    doBlockSelection(sendEvent);
  }
}

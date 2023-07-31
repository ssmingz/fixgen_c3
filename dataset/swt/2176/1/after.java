class PlaceHold {
  int getOffsetAtPoint(int x, int y, int[] trailing) {
    if (((y + getVerticalScrollOffset()) < 0) || ((x + horizontalScrollOffset) < 0)) {
      return -1;
    }
    int bottomIndex = getPartialBottomIndex();
    int height = getLinePixel(bottomIndex + 1);
    if (y > height) {
      return -1;
    }
    int lineIndex = getLineIndex(y);
    int lineOffset = content.getOffsetAtLine(lineIndex);
    TextLayout layout = renderer.getTextLayout(lineIndex);
    x += horizontalScrollOffset - leftMargin;
    y -= getLinePixel(lineIndex);
    int offset = layout.getOffset(x, y, trailing);
    Rectangle rect = layout.getLineBounds(layout.getLineIndex(offset));
    renderer.disposeTextLayout(layout);
    if ((rect.x <= x) && (x <= (rect.x + rect.width))) {
      return offset + lineOffset;
    }
    return -1;
  }
}

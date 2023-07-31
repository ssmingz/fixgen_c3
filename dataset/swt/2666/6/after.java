class PlaceHold {
  void internalRedrawRange(int start, int length) {
    if (length <= 0) {
      return;
    }
    int end = start + length;
    int startLine = content.getLineAtOffset(start);
    int endLine = content.getLineAtOffset(end);
    int partialBottomIndex = getPartialBottomIndex();
    int partialTopIndex = getPartialTopIndex();
    if ((startLine > partialBottomIndex) || (endLine < partialTopIndex)) {
      return;
    }
    if (partialTopIndex > startLine) {
      startLine = partialTopIndex;
      start = 0;
    } else {
      start -= content.getOffsetAtLine(startLine);
    }
    if (partialBottomIndex < endLine) {
      endLine = partialBottomIndex + 1;
      end = 0;
    } else {
      end -= content.getOffsetAtLine(endLine);
    }
    TextLayout layout = renderer.getTextLayout(startLine);
    int lineX = leftMargin - horizontalScrollOffset;
    int startLineY = getLinePixel(startLine);
    int[] offsets = layout.getLineOffsets();
    int startIndex = layout.getLineIndex(Math.min(start, layout.getText().length()));
    if (((wordWrap || visualWrap) && (startIndex > 0)) && (offsets[startIndex] == start)) {
      Rectangle rect = layout.getLineBounds(startIndex - 1);
      rect.x = rect.width;
      rect.width = (clientAreaWidth - rightMargin) - rect.x;
      rect.x += lineX;
      rect.y += startLineY;
      super.redraw(rect.x, rect.y, rect.width, rect.height, false);
    }
    if (startLine == endLine) {
      int endIndex = layout.getLineIndex(Math.min(end, layout.getText().length()));
      if (startIndex == endIndex) {
        Rectangle rect = layout.getBounds(start, end - 1);
        rect.x += lineX;
        rect.y += startLineY;
        super.redraw(rect.x, rect.y, rect.width, rect.height, false);
        renderer.disposeTextLayout(layout);
        return;
      }
    }
    Rectangle startRect = layout.getBounds(start, offsets[startIndex + 1] - 1);
    if (startRect.height == 0) {
      Rectangle bounds = layout.getLineBounds(startIndex);
      startRect.x = bounds.width;
      startRect.y = bounds.y;
      startRect.height = bounds.height;
    }
    startRect.x += lineX;
    startRect.y += startLineY;
    startRect.width = (clientAreaWidth - rightMargin) - startRect.x;
    super.redraw(startRect.x, startRect.y, startRect.width, startRect.height, false);
    if (startLine != endLine) {
      renderer.disposeTextLayout(layout);
      layout = renderer.getTextLayout(endLine);
      offsets = layout.getLineOffsets();
    }
    int endIndex = layout.getLineIndex(Math.min(end, layout.getText().length()));
    Rectangle endRect = layout.getBounds(offsets[endIndex], end - 1);
    if (endRect.height == 0) {
      Rectangle bounds = layout.getLineBounds(endIndex);
      endRect.y = bounds.y;
      endRect.height = bounds.height;
    }
    endRect.x += lineX;
    endRect.y += getLinePixel(endLine);
    super.redraw(endRect.x, endRect.y, endRect.width, endRect.height, false);
    renderer.disposeTextLayout(layout);
    int y = startRect.y + startRect.height;
    if (endRect.y > y) {
      super.redraw(
          leftMargin, y, (clientAreaWidth - rightMargin) - leftMargin, endRect.y - y, false);
    }
  }
}

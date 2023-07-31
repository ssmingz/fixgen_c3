class PlaceHold {
  Point getPointAtOffset(int offset) {
    int lineIndex = content.getLineAtOffset(offset);
    String line = content.getLine(lineIndex);
    int lineOffset = content.getOffsetAtLine(lineIndex);
    int offsetInLine = Math.max(0, offset - lineOffset);
    int lineLength = line.length();
    if (lineIndex < (content.getLineCount() - 1)) {
      int endLineOffset = content.getOffsetAtLine(lineIndex + 1) - 1;
      if ((lineLength < offsetInLine) && (offsetInLine <= endLineOffset)) {
        offsetInLine = lineLength;
      }
    }
    Point point;
    TextLayout layout = renderer.getTextLayout(lineIndex);
    if ((lineLength != 0) && (offsetInLine <= lineLength)) {
      if (offsetInLine == lineLength) {
        offsetInLine = layout.getPreviousOffset(offsetInLine, MOVEMENT_CLUSTER);
        point = layout.getLocation(offsetInLine, true);
      } else {
        switch (caretAlignment) {
          case OFFSET_LEADING:
            point = layout.getLocation(offsetInLine, false);
            break;
          case PREVIOUS_OFFSET_TRAILING:
          default:
            if (offsetInLine == 0) {
              point = layout.getLocation(offsetInLine, false);
            } else {
              offsetInLine = layout.getPreviousOffset(offsetInLine, MOVEMENT_CLUSTER);
              point = layout.getLocation(offsetInLine, true);
            }
            break;
        }
      }
    } else {
      point = new Point(layout.getIndent(), 0);
    }
    renderer.disposeTextLayout(layout);
    point.x += leftMargin - horizontalScrollOffset;
    point.y += getLinePixel(lineIndex);
    return point;
  }
}

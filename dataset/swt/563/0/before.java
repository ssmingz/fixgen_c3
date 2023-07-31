class PlaceHold {
  Rectangle getBoundsAtOffset(int offset) {
    int lineIndex = content.getLineAtOffset(offset);
    int lineOffset = content.getOffsetAtLine(lineIndex);
    String line = content.getLine(lineIndex);
    Rectangle bounds;
    if (line.length() != 0) {
      int offsetInLine = offset - lineOffset;
      TextLayout layout = renderer.getTextLayout(lineIndex);
      if ((caretAlignment == PREVIOUS_OFFSET_TRAILING) && (offsetInLine != 0)) {
        offsetInLine = layout.getPreviousOffset(offsetInLine, MOVEMENT_CLUSTER);
        Point point = layout.getLocation(offsetInLine, true);
        bounds = new Rectangle(point.x, point.y, 0, renderer.getLineHeight());
      } else {
        bounds = layout.getBounds(offsetInLine, offsetInLine);
      }
      renderer.disposeTextLayout(layout);
    } else {
      bounds = new Rectangle(0, 0, 0, renderer.getLineHeight());
    }
    if ((offset == caretOffset) && (!(wordWrap || visualWrap))) {
      int lineEnd = lineOffset + line.length();
      if (offset == lineEnd) {
        bounds.width += getCaretWidth();
      }
    }
    bounds.x += leftMargin - horizontalScrollOffset;
    bounds.y += getLinePixel(lineIndex);
    return bounds;
  }
}

class PlaceHold {
  void draw(int x, int y, int width, int height, boolean clearBackground) {
    if (clearBackground) {
      redraw(x + leftMargin, y + topMargin, width, height, true);
    } else {
      int startLine = (y + verticalScrollOffset) / lineHeight;
      int endY = y + height;
      int paintYFromTopLine = (startLine - topIndex) * lineHeight;
      int topLineOffset = (topIndex * lineHeight) - verticalScrollOffset;
      int paintY = (paintYFromTopLine + topLineOffset) + topMargin;
      int lineCount = (isSingleLine()) ? 1 : content.getLineCount();
      GC gc = getGC();
      Color background = getBackground();
      Color foreground = getForeground();
      for (int i = startLine; (paintY < endY) && (i < lineCount); i++, paintY += lineHeight) {
        String line = content.getLine(i);
        renderer.drawLine(line, i, paintY, gc, background, foreground, clearBackground);
      }
      gc.dispose();
    }
  }
}

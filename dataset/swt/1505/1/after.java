class PlaceHold {
  void performPaint(GC gc, int startLine, int startY, int renderHeight) {
    Rectangle clientArea = getClientArea();
    if (clientArea.width == 0) {
      return;
    }
    Color background = getBackground();
    if (renderHeight > 0) {
      Color foreground = getForeground();
      int lineCount = content.getLineCount();
      int gcStyle = (isMirrored()) ? SWT.RIGHT_TO_LEFT : SWT.LEFT_TO_RIGHT;
      if (isSingleLine()) {
        lineCount = 1;
      }
      int paintY;
      int paintHeight;
      Image lineBuffer;
      GC lineGC;
      boolean doubleBuffer = DOUBLE_BUFFER && (lastPaintTopIndex == topIndex);
      lastPaintTopIndex = topIndex;
      if (doubleBuffer) {
        paintY = 0;
        paintHeight = renderHeight;
        lineBuffer = new Image(getDisplay(), clientArea.width, renderHeight);
        lineGC = new GC(lineBuffer, gcStyle);
        lineGC.setFont(getFont());
        lineGC.setForeground(foreground);
        lineGC.setBackground(background);
      } else {
        paintY = startY;
        paintHeight = startY + renderHeight;
        lineBuffer = null;
        lineGC = gc;
      }
      int paintX = (getClientArea().x + leftMargin) - getHorizontalPixel();
      for (int i = startLine;
          (paintY < paintHeight) && (i < lineCount);
          i++, paintY += lineHeight) {
        String line = content.getLine(i);
        renderer.drawLine(line, i, paintX, paintY, lineGC, background, foreground, true);
      }
      if (paintY < paintHeight) {
        lineGC.setBackground(background);
        lineGC.fillRectangle(0, paintY, clientArea.width, paintHeight - paintY);
      }
      if (doubleBuffer) {
        clearMargin(lineGC, background, clientArea, startY);
        gc.drawImage(lineBuffer, 0, startY);
        lineGC.dispose();
        lineBuffer.dispose();
      }
    }
    clearMargin(gc, background, clientArea, 0);
  }
}

class PlaceHold {
  protected void drawLineSelectionBackground(
      String line, int lineOffset, StyleRange[] styles, int paintY, GC gc, StyledTextBidi bidi) {
    Point selection = parent.internalGetSelection();
    LineCache lineCache = parent.internalGetLineCache();
    StyledTextContent content = getContent();
    int lineLength = line.length();
    int paintX;
    int selectionBackgroundWidth = -1;
    int selectionStart = Math.max(0, selection.x - lineOffset);
    int selectionEnd = selection.y - lineOffset;
    int selectionLength = selectionEnd - selectionStart;
    int horizontalScrollOffset = parent.internalGetHorizontalPixel();
    int leftMargin = getLeftMargin();
    int lineEndSpaceWidth = getLineEndSpaceWidth();
    int lineHeight = getLineHeight();
    boolean wordWrap = parent.internalGetWordWrap();
    boolean isRightOriented = (parent.getStyle() & SWT.MIRRORED) != 0;
    if (((selectionEnd == selectionStart) || (selectionEnd < 0)) || (selectionStart > lineLength)) {
      return;
    }
    if (bidi != null) {
      paintX = parent.getBidiTextPosition(line, selectionStart, bidi);
    } else {
      paintX = getTextPosition(line, lineOffset, selectionStart, filterLineStyles(styles), gc);
    }
    if (selectionEnd > lineLength) {
      if ((parent.getStyle() & SWT.FULL_SELECTION) != 0) {
        selectionBackgroundWidth = Math.max(getClientArea().width, lineCache.getWidth());
      } else {
        selectionLength = lineLength - selectionStart;
      }
    }
    gc.setBackground(parent.getSelectionBackground());
    gc.setForeground(parent.getSelectionForeground());
    if (selectionBackgroundWidth == (-1)) {
      boolean isWrappedLine = false;
      if (wordWrap) {
        int lineEnd = lineOffset + lineLength;
        int lineIndex = content.getLineAtOffset(lineEnd);
        if ((lineIndex < (content.getLineCount() - 1))
            && (content.getOffsetAtLine(lineIndex + 1) == lineEnd)) {
          isWrappedLine = true;
        }
      }
      if (bidi != null) {
        selectionBackgroundWidth =
            parent.getBidiTextPosition(line, selectionStart + selectionLength, bidi) - paintX;
      } else {
        selectionBackgroundWidth =
            getTextWidth(line, lineOffset, selectionStart, selectionLength, styles, paintX, gc);
      }
      if (selectionBackgroundWidth < 0) {
        paintX += selectionBackgroundWidth;
        selectionBackgroundWidth *= -1;
      }
      if ((selectionEnd > lineLength) && (isWrappedLine == false)) {
        selectionEnd = selectionStart + selectionLength;
        if (((bidi != null) && (selectionEnd > 0))
            && (bidi.isRightToLeft(selectionEnd - 1)
                || (isRightOriented && (bidi.isRightToLeft(selectionEnd - 1) == false)))) {
          int lineEndX = bidi.getTextWidth();
          gc.fillRectangle(
              (lineEndX - horizontalScrollOffset) + leftMargin,
              paintY,
              lineEndSpaceWidth,
              lineHeight);
        } else {
          selectionBackgroundWidth += lineEndSpaceWidth;
        }
      }
    }
    if ((bidi != null) && (paintX == 0)) {
      paintX = StyledText.XINSET;
    }
    gc.fillRectangle(
        (paintX - horizontalScrollOffset) + leftMargin,
        paintY,
        selectionBackgroundWidth,
        lineHeight);
  }
}

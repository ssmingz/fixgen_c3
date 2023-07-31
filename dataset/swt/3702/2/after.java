class PlaceHold {
  void drawLine(
      String line,
      int lineIndex,
      int paintY,
      GC gc,
      Color widgetBackground,
      Color widgetForeground,
      boolean clearBackground) {
    int lineOffset = content.getOffsetAtLine(lineIndex);
    int lineLength = line.length();
    int selectionStart = selection.x;
    int selectionEnd = selection.y;
    StyleRange[] styles = new StyleRange[0];
    Color lineBackground = null;
    StyledTextEvent event = getLineStyleData(lineOffset, line);
    StyledTextBidi bidi = null;
    if (event != null) {
      styles = event.styles;
    }
    if (isBidi()) {
      int[] boldStyles = getBoldRanges(styles, lineOffset, lineLength);
      setLineFont(gc, gc.getFont().getFontData()[0], NORMAL);
      bidi =
          new StyledTextBidi(
              gc, tabWidth, line, boldStyles, boldFont, getBidiSegments(line, lineOffset));
    }
    event = getLineBackgroundData(lineOffset, line);
    if (event != null) {
      lineBackground = event.lineBackground;
    }
    if (lineBackground == null) {
      lineBackground = widgetBackground;
    }
    if (clearBackground
        && ((((getStyle() & SWT.FULL_SELECTION) == 0) || (selectionStart > lineOffset))
            || (selectionEnd <= (lineOffset + lineLength)))) {
      gc.setBackground(lineBackground);
      gc.setForeground(lineBackground);
      gc.fillRectangle(0, paintY, getClientArea().width, lineHeight);
    }
    if (selectionStart != selectionEnd) {
      drawLineSelectionBackground(line, lineOffset, styles, paintY, gc, bidi);
    }
    if ((selectionStart != selectionEnd)
        && (((selectionStart >= lineOffset) && (selectionStart < (lineOffset + lineLength)))
            || ((selectionStart < lineOffset) && (selectionEnd > lineOffset)))) {
      styles = getSelectionLineStyles(styles);
    }
    if (isBidi()) {
      int paintX = textWidth(line, lineOffset, 0, 0, styles, 0, gc, bidi);
      drawStyledLine(
          line, lineOffset, 0, styles, paintX, paintY, gc, lineBackground, widgetForeground, bidi);
    } else {
      drawStyledLine(
          line, lineOffset, 0, styles, 0, paintY, gc, lineBackground, widgetForeground, bidi);
    }
  }
}

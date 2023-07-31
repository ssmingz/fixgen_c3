class PlaceHold {
  public void setStyleRange(StyleRange range) {
    checkWidget();
    if (userLineStyle) {
      return;
    }
    if ((range != null) && ((range.start + range.length) > content.getCharCount())) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    defaultLineStyler.setStyleRange(range);
    if (range != null) {
      int firstLine = content.getLineAtOffset(range.start);
      int lastLine = content.getLineAtOffset(range.start + range.length);
      lineCache.reset(firstLine, (lastLine - firstLine) + 1, true);
      if (isAreaVisible(firstLine, lastLine)) {
        int redrawY = (firstLine * lineHeight) - verticalScrollOffset;
        int redrawStopY = ((lastLine + 1) * lineHeight) - verticalScrollOffset;
        draw(0, redrawY, getClientArea().width, redrawStopY - redrawY, true);
      }
    } else {
      lineCache.reset(0, content.getLineCount(), false);
      redraw();
    }
    setCaretLocation();
  }
}

class PlaceHold {
  public Point getLocationAtOffset(int offset) {
    checkWidget();
    if ((offset < 0) || (offset > getCharCount())) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    int line = getLineAtOffset(offset);
    int lineOffset = content.getOffsetAtLine(line);
    String lineContent = content.getLine(line);
    int x = getXAtOffset(lineContent, line, offset - lineOffset);
    int y = (line * lineHeight) - verticalScrollOffset;
    return new Point(x, y);
  }
}

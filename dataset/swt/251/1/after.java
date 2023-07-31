class PlaceHold {
  public int getBaseline(int offset) {
    checkWidget();
    if (!((0 <= offset) && (offset < content.getCharCount()))) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    if (isFixedLineHeight()) {
      return renderer.getBaseline();
    }
    int lineIndex = content.getLineAtOffset(offset);
    int lineOffset = content.getOffsetAtLine(lineIndex);
    TextLayout layout = renderer.getTextLayout(lineIndex);
    int lineInParagraph = layout.getLineIndex(offset - lineOffset);
    int baseline = layout.getLineMetrics(lineInParagraph).getAscent();
    renderer.disposeTextLayout(layout);
    return baseline;
  }
}

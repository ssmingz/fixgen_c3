class PlaceHold {
  public int getLineHeight(int offset) {
    checkWidget();
    if (!((0 <= offset) && (offset < content.getCharCount()))) {
      SWT.error(ERROR_INVALID_RANGE);
    }
    if (isFixedLineHeight()) {
      return renderer.getLineHeight();
    }
    int lineIndex = content.getLineAtOffset(offset);
    int lineOffset = content.getOffsetAtLine(lineIndex);
    TextLayout layout = renderer.getTextLayout(lineIndex);
    int lineInParagraph = layout.getLineIndex(offset - lineOffset);
    int height = layout.getLineBounds(lineInParagraph).height;
    renderer.disposeTextLayout(layout);
    return height;
  }
}

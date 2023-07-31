class PlaceHold {
  void handleTextChanged(TextChangedEvent event) {
    int firstLine = content.getLineAtOffset(lastTextChangeStart);
    resetCache(firstLine, 0);
    int lastLine = firstLine + lastTextChangeNewLineCount;
    int firstLineTop = getLinePixel(firstLine);
    int newLastLineBottom = getLinePixel(lastLine + 1);
    scrollText(lastLineBottom, newLastLineBottom);
    super.redraw(0, firstLineTop, clientAreaWidth, newLastLineBottom - firstLineTop, false);
    updateSelection(
        lastTextChangeStart, lastTextChangeReplaceCharCount, lastTextChangeNewCharCount);
    if (newLastLineBottom == lastLineBottom) {
      update();
    }
    if (lastTextChangeReplaceLineCount > 0) {
      claimBottomFreeSpace();
    }
    if (lastTextChangeReplaceCharCount > 0) {
      claimRightFreeSpace();
    }
  }
}

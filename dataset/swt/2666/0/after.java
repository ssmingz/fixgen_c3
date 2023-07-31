class PlaceHold {
  void handleTextChanged(TextChangedEvent event) {
    int offset = ime.getCompositionOffset();
    if ((offset != (-1)) && (lastTextChangeStart < offset)) {
      ime.setCompositionOffset(
          (offset + lastTextChangeNewCharCount) - lastTextChangeReplaceCharCount);
    }
    int firstLine = content.getLineAtOffset(lastTextChangeStart);
    resetCache(firstLine, 0);
    if ((!isFixedLineHeight()) && (topIndex > firstLine)) {
      topIndex = firstLine;
      topIndexY = 0;
      super.redraw();
    } else {
      int lastLine = firstLine + lastTextChangeNewLineCount;
      int firstLineTop = getLinePixel(firstLine);
      int newLastLineBottom = getLinePixel(lastLine + 1);
      if (lastLineBottom != newLastLineBottom) {
        super.redraw();
      } else {
        super.redraw(0, firstLineTop, clientAreaWidth, newLastLineBottom - firstLineTop, false);
        redrawLinesBullet(renderer.redrawLines);
      }
    }
    renderer.redrawLines = null;
    if (!(blockSelection && (blockXLocation != (-1)))) {
      updateSelection(
          lastTextChangeStart, lastTextChangeReplaceCharCount, lastTextChangeNewCharCount);
    }
    if (((lastTextChangeReplaceLineCount > 0) || wordWrap) || visualWrap) {
      claimBottomFreeSpace();
    }
    if (lastTextChangeReplaceCharCount > 0) {
      claimRightFreeSpace();
    }
    sendAccessibleTextChanged(lastTextChangeStart, lastTextChangeNewCharCount, 0);
    lastCharCount += lastTextChangeNewCharCount;
    lastCharCount -= lastTextChangeReplaceCharCount;
    setAlignment();
  }
}

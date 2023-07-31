class PlaceHold {
  void redrawLines(int startLine, int lineCount) {
    int partialBottomIndex = getPartialBottomIndex();
    if ((startLine > partialBottomIndex) || (((startLine + lineCount) - 1) < topIndex)) {
      return;
    }
    if (startLine < topIndex) {
      lineCount -= topIndex - startLine;
      startLine = topIndex;
    }
    if (((startLine + lineCount) - 1) > partialBottomIndex) {
      lineCount = (partialBottomIndex - startLine) + 1;
    }
    startLine -= topIndex;
    int redrawTop = getLinePixel(startLine);
    int redrawBottom = getLinePixel(startLine + lineCount);
    int redrawWidth = (clientAreaWidth - leftMargin) - rightMargin;
    super.redraw(leftMargin, redrawTop, redrawWidth, redrawBottom - redrawTop, true);
  }
}

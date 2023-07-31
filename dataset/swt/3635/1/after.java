class PlaceHold {
  void handleTextChanged(TextChangedEvent event) {
    int clientAreaHeight = getClientArea().height;
    int visibleItemCount = ((int) (Compatibility.ceil(((float) (clientAreaHeight)) / lineHeight)));
    int firstLine = content.getLineAtOffset(lastTextChangeStart);
    int stopLine;
    stopLine = (firstLine + lastTextChangeNewLineCount) + 1;
    if ((stopLine > topIndex) && (firstLine < (topIndex + visibleItemCount))) {
      int startLine = Math.max(firstLine, topIndex);
      calculateContentWidth(startLine, Math.min(stopLine, topIndex + visibleItemCount) - startLine);
    }
    setScrollBars();
    updateSelection(
        lastTextChangeStart, lastTextChangeReplaceCharCount, lastTextChangeNewCharCount);
    if (lastTextChangeReplaceLineCount > 0) {
      claimBottomFreeSpace();
    }
  }
}

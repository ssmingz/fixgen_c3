class PlaceHold {
  void handleTextChanged(TextChangedEvent event) {
    lineCache.textChanged(
        lastTextChangeStart,
        lastTextChangeNewLineCount,
        lastTextChangeReplaceLineCount,
        lastTextChangeNewCharCount,
        lastTextChangeReplaceCharCount);
    setScrollBars();
    updateSelection(
        lastTextChangeStart, lastTextChangeReplaceCharCount, lastTextChangeNewCharCount);
    if (lastTextChangeReplaceLineCount > 0) {
      claimBottomFreeSpace();
    }
    if (lastTextChangeReplaceCharCount > 0) {
      claimRightFreeSpace();
    }
    if ((lastTextChangeNewLineCount == 0) && (lastTextChangeReplaceLineCount == 0)) {
      int startLine = content.getLineAtOffset(lastTextChangeStart);
      int startY = ((startLine * lineHeight) - verticalScrollOffset) + topMargin;
      GC gc = new GC(this);
      Caret caret = getCaret();
      boolean caretVisible = false;
      if (caret != null) {
        caretVisible = caret.getVisible();
        caret.setVisible(false);
      }
      performPaint(gc, startLine, startY, lineHeight);
      if (caret != null) {
        caret.setVisible(caretVisible);
      }
      gc.dispose();
    }
  }
}

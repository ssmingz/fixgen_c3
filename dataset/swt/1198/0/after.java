class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    int oldLineHeight = renderer.getLineHeight();
    super.setFont(font);
    renderer.setFont(getFont(), tabLength);
    if (isFixedLineHeight()) {
      int lineHeight = renderer.getLineHeight();
      if (lineHeight != oldLineHeight) {
        int vscroll =
            ((getVerticalScrollOffset() * lineHeight) / oldLineHeight) - getVerticalScrollOffset();
        scrollVertical(vscroll, true);
      }
    }
    resetCache(0, content.getLineCount());
    claimBottomFreeSpace();
    calculateScrollBars();
    if (isBidiCaret()) {
      createCaretBitmaps();
    }
    caretDirection = SWT.NULL;
    setCaretLocation();
    super.redraw();
  }
}

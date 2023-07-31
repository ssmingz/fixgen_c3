class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    int oldLineHeight = lineHeight;
    super.setFont(font);
    initializeRenderer();
    if (lineHeight != oldLineHeight) {
      setVerticalScrollOffset((verticalScrollOffset * lineHeight) / oldLineHeight, true);
      claimBottomFreeSpace();
    }
    calculateContentWidth();
    calculateScrollBars();
    if (isBidiCaret()) {
      createCaretBitmaps();
    }
    caretDirection = SWT.NULL;
    setCaretLocation();
    super.redraw();
  }
}

class PlaceHold {
  void reset() {
    ScrollBar verticalBar = getVerticalBar();
    ScrollBar horizontalBar = getHorizontalBar();
    caretOffset = 0;
    caretLine = 0;
    topIndex = 0;
    verticalScrollOffset = 0;
    horizontalScrollOffset = 0;
    resetSelection();
    if (defaultLineStyler != null) {
      removeLineBackgroundListener(defaultLineStyler);
      removeLineStyleListener(defaultLineStyler);
      installDefaultLineStyler();
    }
    calculateContentWidth();
    if (verticalBar != null) {
      verticalBar.setSelection(0);
    }
    if (horizontalBar != null) {
      horizontalBar.setSelection(0);
    }
    setScrollBars();
    setCaretLocation();
    super.redraw();
  }
}

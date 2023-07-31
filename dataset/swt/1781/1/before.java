class PlaceHold {
  public void setWordWrap(boolean wrap) {
    checkWidget();
    if ((getStyle() & SWT.SINGLE) != 0) {
      return;
    }
    if (wrap != wordWrap) {
      wordWrap = wrap;
      calculateContentWidth();
      horizontalScrollOffset = 0;
      ScrollBar horizontalBar = getHorizontalBar();
      if (horizontalBar != null) {
        horizontalBar.setVisible(!wordWrap);
      }
      setScrollBars();
      setCaretLocation();
      super.redraw();
    }
  }
}

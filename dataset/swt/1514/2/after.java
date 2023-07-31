class PlaceHold {
  public void setWordWrap(boolean wrap) {
    checkWidget();
    if ((getStyle() & SWT.SINGLE) != 0) {
      return;
    }
    if (wrap != wordWrap) {
      ScrollBar horizontalBar = getHorizontalBar();
      wordWrap = wrap;
      if (wordWrap) {
        logicalContent = content;
        content = new WrappedContent(renderer, logicalContent);
      } else {
        content = logicalContent;
      }
      calculateContentWidth();
      horizontalScrollOffset = 0;
      if (horizontalBar != null) {
        horizontalBar.setVisible(!wordWrap);
      }
      setScrollBars();
      setCaretLocation();
      super.redraw();
    }
  }
}

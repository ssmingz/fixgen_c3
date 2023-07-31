class PlaceHold {
  void handleResize(Event event) {
    int oldHeight = clientAreaHeight;
    int oldWidth = clientAreaWidth;
    Rectangle clientArea = getClientArea();
    clientAreaHeight = clientArea.height;
    clientAreaWidth = clientArea.width;
    if (oldWidth != clientAreaWidth) {
      if (rightMargin > 0) {
        int x = (oldWidth < clientAreaWidth ? oldWidth : clientAreaWidth) - rightMargin;
        super.redraw(x, 0, rightMargin, oldHeight, false);
      }
    }
    if (oldHeight != clientAreaHeight) {
      if (bottomMargin > 0) {
        int y = (oldHeight < clientAreaHeight ? oldHeight : clientAreaHeight) - bottomMargin;
        super.redraw(0, y, oldWidth, bottomMargin, false);
      }
    }
    if (wordWrap) {
      if (oldWidth != clientAreaWidth) {
        renderer.reset(0, content.getLineCount());
        verticalScrollOffset = -1;
        renderer.calculateIdle();
        super.redraw();
      }
      if (oldHeight != clientAreaHeight) {
        if (oldHeight == 0) {
          topIndexY = 0;
        }
        setScrollBars(true);
      }
      setCaretLocation();
    } else {
      renderer.calculateClientArea();
      setScrollBars(true);
      claimRightFreeSpace();
      if (clientAreaWidth != 0) {
        ScrollBar horizontalBar = getHorizontalBar();
        if ((horizontalBar != null) && horizontalBar.getVisible()) {
          if (horizontalScrollOffset != horizontalBar.getSelection()) {
            horizontalBar.setSelection(horizontalScrollOffset);
            horizontalScrollOffset = horizontalBar.getSelection();
          }
        }
      }
    }
    claimBottomFreeSpace();
  }
}

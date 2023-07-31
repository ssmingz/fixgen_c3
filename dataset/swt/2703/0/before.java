class PlaceHold {
  void setVerticalScrollOffset(int pixelOffset, boolean adjustScrollBar) {
    Rectangle clientArea;
    ScrollBar verticalBar = getVerticalBar();
    int verticalIncrement = getVerticalIncrement();
    if (pixelOffset == verticalScrollOffset) {
      return;
    }
    if ((verticalBar != null) && adjustScrollBar) {
      verticalBar.setSelection(pixelOffset);
    }
    clientArea = getClientArea();
    scroll(0, 0, 0, pixelOffset - verticalScrollOffset, clientArea.width, clientArea.height, true);
    if (verticalIncrement != 0) {
      int oldTopIndex = topIndex;
      topIndex = Compatibility.ceil(pixelOffset, verticalIncrement);
      if (topIndex != oldTopIndex) {
        contentWidth.calculate(topIndex, (getPartialBottomIndex() - topIndex) + 1);
        setHorizontalScrollBar();
      }
    }
    verticalScrollOffset = pixelOffset;
    setCaretLocation();
  }
}

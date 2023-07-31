class PlaceHold {
  boolean setVerticalScrollOffset(int pixelOffset, boolean adjustScrollBar) {
    Rectangle clientArea;
    ScrollBar verticalBar = getVerticalBar();
    if (pixelOffset == verticalScrollOffset) {
      return false;
    }
    if ((verticalBar != null) && adjustScrollBar) {
      verticalBar.setSelection(pixelOffset);
    }
    clientArea = getClientArea();
    scroll(0, 0, 0, pixelOffset - verticalScrollOffset, clientArea.width, clientArea.height, true);
    verticalScrollOffset = pixelOffset;
    calculateTopIndex();
    setCaretLocation();
    return true;
  }
}

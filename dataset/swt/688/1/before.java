class PlaceHold {
  void scrollHorizontal(int pixels) {
    Rectangle clientArea;
    if (pixels == 0) {
      return;
    }
    clientArea = getClientArea();
    if (pixels > 0) {
      int sourceX = leftMargin + pixels;
      int scrollWidth = (clientArea.width - sourceX) - rightMargin;
      int scrollHeight = (clientArea.height - topMargin) - bottomMargin;
      scroll(leftMargin, topMargin, sourceX, topMargin, scrollWidth, scrollHeight, true);
      if (sourceX > scrollWidth) {
        super.redraw(leftMargin + scrollWidth, topMargin, pixels - scrollWidth, scrollHeight, true);
      }
    } else {
      int destinationX = leftMargin - pixels;
      int scrollWidth = (clientArea.width - destinationX) - rightMargin;
      int scrollHeight = (clientArea.height - topMargin) - bottomMargin;
      scroll(destinationX, topMargin, leftMargin, topMargin, scrollWidth, scrollHeight, true);
      if (destinationX > scrollWidth) {
        super.redraw(
            leftMargin + scrollWidth, topMargin, (-pixels) - scrollWidth, scrollHeight, true);
      }
    }
    horizontalScrollOffset += pixels;
    int oldColumnX = columnX - pixels;
    setCaretLocation();
    columnX = oldColumnX;
  }
}

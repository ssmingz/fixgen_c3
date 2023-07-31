class PlaceHold {
  public void redraw(int x, int y, int width, int height, boolean all) {
    super.redraw(x, y, width, height, all);
    if (height > 0) {
      int lineCount = content.getLineCount();
      int startLine = (getTopPixel() + y) / lineHeight;
      int endLine = startLine + Compatibility.ceil(height, lineHeight);
      int itemCount;
      startLine = Math.min(startLine, lineCount);
      itemCount = Math.min(endLine, lineCount) - startLine;
      contentWidth.reset(startLine, itemCount);
      itemCount = (getPartialBottomIndex() - topIndex) + 1;
      contentWidth.calculate(topIndex, itemCount);
      setHorizontalScrollBar();
    }
  }
}

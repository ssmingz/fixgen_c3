class PlaceHold {
  public void redraw(int x, int y, int width, int height, boolean all) {
    super.redraw(x, y, width, height, all);
    if (height > 0) {
      int firstLine = getLineIndex(y);
      int lastLine = getLineIndex(y + height);
      resetCache(firstLine, (lastLine - firstLine) + 1);
      doMouseLinkCursor();
    }
  }
}

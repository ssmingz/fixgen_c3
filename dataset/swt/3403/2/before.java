class PlaceHold {
  public Rectangle getBounds(int start, int end) {
    checkLayout();
    int length = text.length();
    if (length == 0) {
      return new Rectangle(0, 0, 0, 0);
    }
    if (start > end) {
      return new Rectangle(0, 0, 0, 0);
    }
    start = Math.min(Math.max(0, start), length - 1);
    end = Math.min(Math.max(0, end), length - 1);
    computeRuns(null);
    int startLine = getLineIndex(start);
    int endLine = getLineIndex(end);
    Rectangle rect = new Rectangle(0, 0, 0, 0);
    rect.y = lineY[startLine];
    rect.height = lineY[endLine + 1] - rect.y;
    if (startLine == endLine) {
      rect.x = getLocation(start, LEAD).x;
      rect.width = getLocation(end, TRAIL).x - rect.x;
    } else {
      while (startLine <= endLine) {
        rect.width = Math.max(rect.width, lineWidth[startLine++]);
      }
    }
    return rect;
  }
}

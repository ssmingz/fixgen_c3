class PlaceHold {
  void moveRectangles(int xChange, int yChange) {
    if (bounds == null) {
      return;
    }
    if ((xChange < 0) && ((style & SWT.LEFT) == 0)) {
      xChange = 0;
    }
    if ((xChange > 0) && ((style & SWT.RIGHT) == 0)) {
      xChange = 0;
    }
    if ((yChange < 0) && ((style & SWT.UP) == 0)) {
      yChange = 0;
    }
    if ((yChange > 0) && ((style & SWT.DOWN) == 0)) {
      yChange = 0;
    }
    if ((xChange == 0) && (yChange == 0)) {
      return;
    }
    if ((parent != null) && ((parent.style & SWT.MIRRORED) != 0)) {
      xChange *= -1;
    }
    bounds.x += xChange;
    bounds.y += yChange;
    for (int i = 0; i < rectangles.length; i++) {
      rectangles[i].x += xChange;
      rectangles[i].y += yChange;
    }
  }
}

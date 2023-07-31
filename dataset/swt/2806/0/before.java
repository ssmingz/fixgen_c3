class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    Point titleSize = getTitleSize();
    Point size;
    if (layout != null) {
      size = layout.computeSize(this, wHint, hHint, changed);
    } else {
      size = minimumSize();
    }
    int width = size.x;
    int height = size.y;
    if (width == 0) {
      width = DEFAULT_WIDTH;
    }
    if (height == 0) {
      height = DEFAULT_HEIGHT;
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    Rectangle trim = computeTrim(0, 0, width, height);
    width = Math.max(trim.width, titleSize.x + 6);
    height = trim.height + titleSize.y;
    return new Point(width, height);
  }
}

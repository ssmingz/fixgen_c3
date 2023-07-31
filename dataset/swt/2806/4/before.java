class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    PhDim_t dim = new PhDim_t();
    if (!OS.PtWidgetIsRealized(handle)) {
      OS.PtExtentWidget(handle);
    }
    OS.PtWidgetPreferredSize(handle, dim);
    int width = dim.w;
    int height = dim.h;
    Point size;
    if (layout != null) {
      size = layout.computeSize(this, wHint, hHint, changed);
    } else {
      size = minimumSize(wHint, hHint, changed);
    }
    if (size.x == 0) {
      size.x = DEFAULT_WIDTH;
    }
    if (size.y == 0) {
      size.y = DEFAULT_HEIGHT;
    }
    if (wHint != SWT.DEFAULT) {
      size.x = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      size.y = hHint;
    }
    width = Math.max(width, size.x);
    height = Math.max(height, size.y);
    Rectangle trim = computeTrim(0, 0, width, height);
    width = trim.width;
    height = trim.height;
    return new Point(width, height);
  }
}

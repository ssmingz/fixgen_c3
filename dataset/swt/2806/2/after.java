class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    Point size;
    if (layout != null) {
      if ((wHint == SWT.DEFAULT) || (hHint == SWT.DEFAULT)) {
        changed |= (state & LAYOUT_CHANGED) != 0;
        state &= ~LAYOUT_CHANGED;
        size = layout.computeSize(this, wHint, hHint, changed);
      } else {
        size = new Point(wHint, hHint);
      }
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
    Rectangle trim = computeTrim(0, 0, size.x, size.y);
    return new Point(trim.width, trim.height);
  }
}

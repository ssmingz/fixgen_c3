class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    int height = 0;
    NSControl widget = ((NSControl) (view));
    NSSize size = widget.cell().cellSize();
    width = ((int) (Math.ceil(size.width)));
    height = ((int) (Math.ceil(size.height)));
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
    int border = getBorderWidth();
    width += border * 2;
    height += border * 2;
    return new Point(width, height);
  }
}

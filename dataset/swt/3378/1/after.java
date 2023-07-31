class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    NSSlider widget = ((NSSlider) (view));
    NSSize size = widget.cell().cellSizeForBounds(widget.bounds());
    int width = DEFAULT_WIDTH;
    int height = DEFAULT_HEIGHT;
    if ((style & SWT.HORIZONTAL) != 0) {
      height = ((int) (Math.ceil(size.height)));
      width = height * 10;
    } else {
      width = ((int) (Math.ceil(size.width)));
      height = width * 10;
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    return new Point(width, height);
  }
}

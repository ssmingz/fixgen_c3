class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    int height = 0;
    NSControl widget = ((NSControl) (view));
    NSRect rect = new NSRect();
    rect.width = rect.height = Float.MAX_VALUE;
    NSSize size = widget.cell().cellSizeForBounds(rect);
    width = ((int) (Math.ceil(size.width)));
    height = ((int) (Math.ceil(size.height)));
    if (hHint != SWT.DEFAULT) {
      if (((style & SWT.READ_ONLY) != 0) || (hHint < height)) {
        height = hHint;
      }
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    return new Point(width, height);
  }
}

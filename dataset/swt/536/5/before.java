class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    if ((style & SWT.ARROW) != 0) {
      int width = (wHint != SWT.DEFAULT) ? wHint : 23;
      int height = (hHint != SWT.DEFAULT) ? hHint : 23;
      return new Point(width, height);
    }
    NSSize size = ((NSButton) (view)).cell().cellSize();
    int width = ((int) (0.5F + size.width));
    int height = ((int) (0.5F + size.height));
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    return new Point(width, height);
  }
}

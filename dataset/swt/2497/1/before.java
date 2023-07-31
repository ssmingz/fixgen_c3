class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = DEFAULT_WIDTH;
    int height = DEFAULT_HEIGHT;
    if ((style & SWT.SEPARATOR) != 0) {
      float lineWidth = ((NSBox) (view)).borderWidth();
      if ((style & SWT.HORIZONTAL) != 0) {
        height = ((int) (Math.ceil(lineWidth * 2)));
      } else {
        width = ((int) (Math.ceil(lineWidth * 2)));
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
    if (isImage) {
      if (image != null) {
        NSImage nsimage = image.handle;
        NSSize size = nsimage.size();
        width = ((int) (size.width));
        height = ((int) (size.height));
      } else {
        width = height = 0;
      }
    } else {
      NSSize size = null;
      if (((style & SWT.WRAP) != 0) && (wHint != SWT.DEFAULT)) {
        NSRect rect = new NSRect();
        rect.width = wHint;
        rect.height = (hHint != SWT.DEFAULT) ? hHint : Float.MAX_VALUE;
        size = textView.cell().cellSizeForBounds(rect);
      } else {
        size = textView.cell().cellSize();
      }
      width = ((int) (Math.ceil(size.width)));
      height = ((int) (Math.ceil(size.height)));
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

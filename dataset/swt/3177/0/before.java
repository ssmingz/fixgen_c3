class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    NSRect oldRect = view.frame();
    int width = DEFAULT_WIDTH;
    int height = DEFAULT_HEIGHT;
    if ((style & SWT.SEPARATOR) != 0) {
      ((NSBox) (view)).sizeToFit();
      NSRect newRect = view.frame();
      width = ((int) (newRect.width));
      height = ((int) (newRect.height));
      view.setFrame(oldRect);
    } else if (isImage) {
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
      width = ((int) (0.5F + size.width));
      height = ((int) (0.5F + size.height));
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

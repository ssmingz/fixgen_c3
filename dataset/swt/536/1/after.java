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
      NSRect rect = new NSRect();
      rect.width = (wHint != SWT.DEFAULT) ? wHint : Float.MAX_VALUE;
      rect.height = (hHint != SWT.DEFAULT) ? hHint : Float.MAX_VALUE;
      NSSize size = textView.cell().cellSizeForBounds(rect);
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

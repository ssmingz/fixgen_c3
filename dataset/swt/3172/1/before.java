class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    int height = 0;
    if ((style & SWT.SINGLE) != 0) {
      NSTextField widget = ((NSTextField) (view));
      NSRect rect = new NSRect();
      rect.width = rect.height = Float.MAX_VALUE;
      NSSize size = widget.cell().cellSizeForBounds(rect);
      width = ((int) (Math.ceil(size.width)));
      height = ((int) (Math.ceil(size.height)));
    } else {
      NSTextView widget = ((NSTextView) (view));
      NSSize oldSize = null;
      NSTextContainer textContainer = widget.textContainer();
      if ((style & SWT.WRAP) != 0) {
        widget.setHorizontallyResizable(true);
        textContainer.setWidthTracksTextView(false);
        oldSize = textContainer.containerSize();
        NSSize csize = new NSSize();
        csize.width = (wHint != SWT.DEFAULT) ? wHint : Float.MAX_VALUE;
        csize.height = (hHint != SWT.DEFAULT) ? hHint : Float.MAX_VALUE;
        textContainer.setContainerSize(csize);
      }
      NSRect oldRect = widget.frame();
      widget.sizeToFit();
      NSRect newRect = widget.frame();
      widget.setFrame(oldRect);
      if ((style & SWT.WRAP) != 0) {
        widget.setHorizontallyResizable(false);
        textContainer.setWidthTracksTextView(true);
        textContainer.setContainerSize(oldSize);
      }
      width = ((int) (newRect.width + 1));
      height = ((int) (newRect.height + 1));
    }
    if (width <= 0) {
      width = DEFAULT_WIDTH;
    }
    if (height <= 0) {
      height = DEFAULT_HEIGHT;
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    Rectangle trim = computeTrim(0, 0, width, height);
    width = trim.width;
    height = trim.height;
    return new Point(width, height);
  }
}

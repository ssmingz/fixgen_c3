class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int width = 0;
    int height = 0;
    if ((style & SWT.SINGLE) != 0) {
      NSCell cell = ((NSTextField) (view)).cell();
      NSSize size = cell.cellSize();
      NSString str = ((NSTextField) (view)).stringValue();
      if (str.length() > 0) {
        width = ((int) (Math.ceil(size.width)));
      }
      height = ((int) (Math.ceil(size.height)));
      Point border = null;
      if (((style & SWT.BORDER) != 0) && ((wHint != SWT.DEFAULT) || (hHint != SWT.DEFAULT))) {
        NSRect insets = cell.titleRectForBounds(new NSRect());
        border = new Point(-((int) (Math.ceil(insets.width))), -((int) (Math.ceil(insets.height))));
        width -= border.x;
        height -= border.y;
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
      if (border != null) {
        width += border.x;
        height += border.y;
      }
    } else {
      NSLayoutManager layoutManager = ((NSLayoutManager) (new NSLayoutManager().alloc().init()));
      NSTextContainer textContainer = ((NSTextContainer) (new NSTextContainer().alloc()));
      NSSize size = new NSSize();
      size.width = size.height = OS.MAX_TEXT_CONTAINER_SIZE;
      if ((style & SWT.WRAP) != 0) {
        if (wHint != SWT.DEFAULT) {
          size.width = wHint;
        }
        if (hHint != SWT.DEFAULT) {
          size.height = hHint;
        }
      }
      textContainer.initWithContainerSize(size);
      layoutManager.addTextContainer(textContainer);
      NSTextStorage textStorage = ((NSTextStorage) (new NSTextStorage().alloc().init()));
      textStorage.setAttributedString(((NSTextView) (view)).textStorage());
      layoutManager.setTextStorage(textStorage);
      layoutManager.glyphRangeForTextContainer(textContainer);
      NSRect rect = layoutManager.usedRectForTextContainer(textContainer);
      width =
          (layoutManager.numberOfGlyphs() == 0) ? DEFAULT_WIDTH : ((int) (Math.ceil(rect.width)));
      height = ((int) (Math.ceil(rect.height)));
      textStorage.release();
      textContainer.release();
      layoutManager.release();
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
    }
    return new Point(width, height);
  }
}

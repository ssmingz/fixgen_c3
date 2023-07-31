class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    if ((wHint != SWT.DEFAULT) && (wHint < 0)) {
      wHint = 0;
    }
    if ((hHint != SWT.DEFAULT) && (hHint < 0)) {
      hHint = 0;
    }
    int width = 0;
    int height = 0;
    NSLayoutManager layoutManager = ((NSLayoutManager) (new NSLayoutManager().alloc().init()));
    NSTextContainer textContainer = ((NSTextContainer) (new NSTextContainer().alloc()));
    NSSize size = new NSSize();
    size.width = size.height = Float.MAX_VALUE;
    if (wHint != SWT.DEFAULT) {
      size.width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      size.height = hHint;
    }
    textContainer.initWithContainerSize(size);
    textContainer.setLineFragmentPadding(2);
    layoutManager.addTextContainer(textContainer);
    NSTextStorage textStorage = ((NSTextStorage) (new NSTextStorage().alloc().init()));
    textStorage.setAttributedString(((NSTextView) (view)).textStorage());
    layoutManager.setTextStorage(textStorage);
    layoutManager.glyphRangeForTextContainer(textContainer);
    NSRect rect = layoutManager.usedRectForTextContainer(textContainer);
    width = (layoutManager.numberOfGlyphs() == 0) ? DEFAULT_WIDTH : ((int) (Math.ceil(rect.width)));
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
    size.width = width;
    size.height = height;
    int border = (hasBorder()) ? OS.NSBezelBorder : OS.NSNoBorder;
    size = NSScrollView.frameSizeForContentSize(size, false, false, border);
    width = ((int) (size.width));
    height = ((int) (size.height));
    if (!hasBorder()) {
      width += 2;
      height += 2;
    }
    return new Point(width, height);
  }
}

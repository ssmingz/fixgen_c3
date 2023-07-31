class PlaceHold {
  public Rectangle getBounds() {
    checkLayout();
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      computeRuns();
      NSRect rect = layoutManager.usedRectForTextContainer(textContainer);
      if (wrapWidth != (-1)) {
        rect.width = wrapWidth;
      }
      if (text.length() == 0) {
        Font font = (this.font != null) ? this.font : device.systemFont;
        NSFont nsFont = font.handle;
        rect.height = layoutManager.defaultLineHeightForFont(nsFont);
      }
      rect.height = Math.max(rect.height, ascent + descent) + spacing;
      return new Rectangle(0, 0, ((int) (rect.width)), ((int) (rect.height)));
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

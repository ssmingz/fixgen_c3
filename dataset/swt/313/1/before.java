class PlaceHold {
  public Rectangle getLineBounds(int lineIndex) {
    checkLayout();
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      computeRuns();
      if (!((0 <= lineIndex) && (lineIndex < lineBounds.length))) {
        SWT.error(ERROR_INVALID_RANGE);
      }
      NSRect rect = lineBounds[lineIndex];
      return new Rectangle(
          ((int) (rect.x)), ((int) (rect.y)), ((int) (rect.width)), ((int) (rect.height)));
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

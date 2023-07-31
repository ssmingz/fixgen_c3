class PlaceHold {
  public void setWidth(int width) {
    checkLayout();
    if ((width < (-1)) || (width == 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (this.wrapWidth == width) {
      return;
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      freeRuns();
      this.wrapWidth = width;
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

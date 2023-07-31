class PlaceHold {
  public void setAscent(int ascent) {
    checkLayout();
    if (ascent < (-1)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (this.ascent == ascent) {
      return;
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      freeRuns();
      this.ascent = ascent;
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

class PlaceHold {
  public void setDescent(int descent) {
    checkLayout();
    if (descent < (-1)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (this.descent == descent) {
      return;
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      freeRuns();
      this.descent = descent;
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

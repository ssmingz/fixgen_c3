class PlaceHold {
  public void setSpacing(int spacing) {
    checkLayout();
    if (spacing < 0) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (this.spacing == spacing) {
      return;
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      freeRuns();
      this.spacing = spacing;
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

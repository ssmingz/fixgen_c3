class PlaceHold {
  public void setJustify(boolean justify) {
    checkLayout();
    if (justify == this.justify) {
      return;
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      freeRuns();
      this.justify = justify;
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

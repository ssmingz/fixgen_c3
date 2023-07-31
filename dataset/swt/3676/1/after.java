class PlaceHold {
  public void setIndent(int indent) {
    checkLayout();
    if (indent < 0) {
      return;
    }
    if (this.indent == indent) {
      return;
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      freeRuns();
      this.indent = indent;
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

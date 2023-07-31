class PlaceHold {
  public void setSegments(int[] segments) {
    checkLayout();
    if ((this.segments == null) && (segments == null)) {
      return;
    }
    if ((this.segments != null) && (segments != null)) {
      if (this.segments.length == segments.length) {
        int i;
        for (i = 0; i < segments.length; i++) {
          if (this.segments[i] != segments[i]) {
            break;
          }
        }
        if (i == segments.length) {
          return;
        }
      }
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      freeRuns();
      this.segments = segments;
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

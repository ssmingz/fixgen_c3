class PlaceHold {
  void add(int[] pointArray, int count) {
    count = (count / 2) * 2;
    if (count <= 2) {
      return;
    }
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      int polyRgn = polyRgn(pointArray, count);
      OS.UnionRgn(handle, polyRgn, handle);
      OS.DisposeRgn(polyRgn);
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

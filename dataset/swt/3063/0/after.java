class PlaceHold {
  public int getNextOffset(int offset, int movement) {
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      return _getOffset(offset, movement, true);
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

class PlaceHold {
  public int getPreviousOffset(int index, int movement) {
    NSAutoreleasePool pool = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      return _getOffset(index, movement, false);
    } finally {
      if (pool != null) {
        pool.release();
      }
    }
  }
}

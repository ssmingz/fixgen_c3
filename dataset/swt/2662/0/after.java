class PlaceHold {
  boolean runAsyncMessages(boolean all) {
    NSAutoreleasePool localPool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    try {
      return synchronizer.runAsyncMessages(all);
    } finally {
      localPool.release();
    }
  }
}

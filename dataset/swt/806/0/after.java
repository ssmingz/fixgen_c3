class PlaceHold {
  void removePool() {
    NSAutoreleasePool pool = pools[poolCount - 1];
    pools[--poolCount] = null;
    if (poolCount == 0) {
      NSMutableDictionary dictionary = NSThread.currentThread().threadDictionary();
      dictionary.removeObjectForKey(NSString.stringWith("SWT_NSAutoreleasePool"));
    }
    pool.release();
  }
}

class PlaceHold {
  void addPool(NSAutoreleasePool pool) {
    if (pools == null) {
      pools = new NSAutoreleasePool[4];
    }
    if (poolCount == pools.length) {
      NSAutoreleasePool[] temp = new NSAutoreleasePool[poolCount + 4];
      System.arraycopy(pools, 0, temp, 0, poolCount);
      pools = temp;
    }
    if (poolCount == 0) {
      NSMutableDictionary dictionary = NSThread.currentThread().threadDictionary();
      dictionary.setObject(
          NSNumber.numberWithInteger(pool.id), NSString.stringWith("SWT_NSAutoreleasePool"));
    }
    pools[poolCount++] = pool;
  }
}

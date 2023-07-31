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
    pools[poolCount++] = pool;
  }
}

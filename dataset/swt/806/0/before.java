class PlaceHold {
  void removePool() {
    NSAutoreleasePool pool = pools[poolCount - 1];
    pools[--poolCount] = null;
    pool.release();
  }
}

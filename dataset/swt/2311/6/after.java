class PlaceHold {
  static final int VtblCall(int fnNumber, int ppVtbl, nsID arg0, long[] arg1) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1);
    } finally {
      lock.unlock();
    }
  }
}

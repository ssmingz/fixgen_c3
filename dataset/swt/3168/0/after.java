class PlaceHold {
  static final int VtblCall(int fnNumber, int ppVtbl, byte[] arg0, int[] arg1, long[] arg2) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2);
    } finally {
      lock.unlock();
    }
  }
}

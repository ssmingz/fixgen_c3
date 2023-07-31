class PlaceHold {
  static final int VtblCall(
      int fnNumber,
      int ppVtbl,
      byte[] arg0,
      byte[] arg1,
      byte[] arg2,
      int arg3,
      int arg4,
      long[] arg5) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2, arg3, arg4, arg5);
    } finally {
      lock.unlock();
    }
  }
}

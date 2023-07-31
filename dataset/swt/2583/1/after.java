class PlaceHold {
  static final int VtblCall(
      int fnNumber,
      int ppVtbl,
      nsID arg0,
      byte[] arg1,
      byte[] arg2,
      long arg3,
      byte[] arg4,
      byte[] arg5) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2, arg3, arg4, arg5);
    } finally {
      lock.unlock();
    }
  }
}

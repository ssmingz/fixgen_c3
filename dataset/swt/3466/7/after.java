class PlaceHold {
  static final int VtblCall(
      int fnNumber,
      int ppVtbl,
      int arg0,
      char[] arg1,
      char[] arg2,
      char[] arg3,
      int[] arg4,
      int[] arg5) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2, arg3, arg4, arg5);
    } finally {
      lock.unlock();
    }
  }
}

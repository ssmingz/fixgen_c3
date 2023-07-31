class PlaceHold {
  static final int VtblCall(
      int fnNumber,
      int ppVtbl,
      int arg0,
      char[] arg1,
      char[] arg2,
      int[] arg3,
      char[] arg4,
      int[] arg5,
      int[] arg6) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2, arg3, arg4, arg5, arg6);
    } finally {
      lock.unlock();
    }
  }
}

class PlaceHold {
  static final int VtblCall(
      int fnNumber,
      int ppVtbl,
      long arg0,
      char[] arg1,
      char[] arg2,
      int arg3,
      char[] arg4,
      char[] arg5,
      char[] arg6,
      char[] arg7,
      int[] arg8,
      int[] arg9) {
    lock.lock();
    try {
      return _VtblCall(
          fnNumber, ppVtbl, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
    } finally {
      lock.unlock();
    }
  }
}

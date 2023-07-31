class PlaceHold {
  static final int VtblCall(
      int fnNumber,
      int ppVtbl,
      int arg0,
      int arg1,
      int arg2,
      int arg3,
      char[] arg4,
      int arg5,
      int arg6,
      int arg7,
      int arg8,
      int arg9,
      int[] arg10,
      int[] arg11) {
    lock.lock();
    try {
      return _VtblCall(
          fnNumber, ppVtbl, arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10,
          arg11);
    } finally {
      lock.unlock();
    }
  }
}

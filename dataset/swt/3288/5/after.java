class PlaceHold {
  static final int VtblCall(
      int fnNumber, int ppVtbl, long arg0, char[] arg1, char[] arg2, char[] arg3, int[] arg4) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2, arg3, arg4);
    } finally {
      lock.unlock();
    }
  }
}

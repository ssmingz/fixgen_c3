class PlaceHold {
  static final int VtblCall(
      int fnNumber, int ppVtbl, int arg0, int arg1, char[] arg2, int arg3, long arg4, int arg5) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2, arg3, arg4, arg5);
    } finally {
      lock.unlock();
    }
  }
}

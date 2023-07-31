class PlaceHold {
  static final int VtblCall(
      int fnNumber, int ppVtbl, char[] arg0, int arg1, long arg2, long arg3, long arg4) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2, arg3, arg4);
    } finally {
      lock.unlock();
    }
  }
}

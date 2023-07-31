class PlaceHold {
  static final int VtblCall(
      int fnNumber,
      int ppVtbl,
      long arg0,
      long arg1,
      long arg2,
      long arg3,
      long arg4,
      long arg5,
      long arg6) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2, arg3, arg4, arg5, arg6);
    } finally {
      lock.unlock();
    }
  }
}

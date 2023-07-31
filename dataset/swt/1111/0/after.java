class PlaceHold {
  static final int VtblCall(
      int fnNumber, int ppVtbl, int arg0, int arg1, int arg2, int arg3, int arg4) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2, arg3, arg4);
    } finally {
      lock.unlock();
    }
  }
}

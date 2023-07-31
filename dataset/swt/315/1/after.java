class PlaceHold {
  static final int VtblCall(int fnNumber, int ppVtbl, long arg0, long arg1, int arg2, char[] arg3) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2, arg3);
    } finally {
      lock.unlock();
    }
  }
}

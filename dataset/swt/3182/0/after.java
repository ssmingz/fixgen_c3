class PlaceHold {
  static final int VtblCall(int fnNumber, int ppVtbl, char[] arg0, int arg1, int[] arg2) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2);
    } finally {
      lock.unlock();
    }
  }
}

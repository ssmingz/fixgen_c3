class PlaceHold {
  static final int VtblCall(int fnNumber, int ppVtbl, long arg0, byte[] arg1, char[] arg2) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2);
    } finally {
      lock.unlock();
    }
  }
}

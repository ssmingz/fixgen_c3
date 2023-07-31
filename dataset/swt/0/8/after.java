class PlaceHold {
  static final int VtblCall(int fnNumber, int ppVtbl, short[] arg0) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0);
    } finally {
      lock.unlock();
    }
  }
}

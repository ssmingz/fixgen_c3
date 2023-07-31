class PlaceHold {
  static final int VtblCall(
      int fnNumber, int ppVtbl, byte[] arg0, int arg1, nsID arg2, int[] arg3) {
    lock.lock();
    try {
      return _VtblCall(fnNumber, ppVtbl, arg0, arg1, arg2, arg3);
    } finally {
      lock.unlock();
    }
  }
}

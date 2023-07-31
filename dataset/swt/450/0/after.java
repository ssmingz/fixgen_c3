class PlaceHold {
  public static final long glXGetCurrentContext() {
    lock.lock();
    try {
      return _glXGetCurrentContext();
    } finally {
      lock.unlock();
    }
  }
}

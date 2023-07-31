class PlaceHold {
  public static final int glXGetCurrentContext() {
    lock.lock();
    try {
      return _glXGetCurrentContext();
    } finally {
      lock.unlock();
    }
  }
}

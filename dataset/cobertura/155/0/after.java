class PlaceHold {
  public boolean containsInstrumentationInfo() {
    lock.lock();
    try {
      return this.containsInstrumentationInfo;
    } finally {
      lock.unlock();
    }
  }
}

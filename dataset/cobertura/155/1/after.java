class PlaceHold {
  public void setContainsInstrumentationInfo() {
    lock.lock();
    try {
      this.containsInstrumentationInfo = true;
    } finally {
      lock.unlock();
    }
  }
}

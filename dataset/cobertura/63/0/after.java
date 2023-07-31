class PlaceHold {
  public Collection getClasses() {
    lock.lock();
    try {
      return this.classes.values();
    } finally {
      lock.unlock();
    }
  }
}

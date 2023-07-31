class PlaceHold {
  public SortedSet getClasses() {
    lock.lock();
    try {
      return new TreeSet(this.children.values());
    } finally {
      lock.unlock();
    }
  }
}

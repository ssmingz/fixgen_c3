class PlaceHold {
  public SortedSet getPackages() {
    lock.lock();
    try {
      return new TreeSet(this.children.values());
    } finally {
      lock.unlock();
    }
  }
}

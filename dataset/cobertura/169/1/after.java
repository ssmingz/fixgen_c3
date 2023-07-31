class PlaceHold {
  public SortedSet getLines() {
    lock.lock();
    try {
      return new TreeSet(this.children.values());
    } finally {
      lock.unlock();
    }
  }
}

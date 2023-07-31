class PlaceHold {
  public boolean contains(String name) {
    lock.lock();
    try {
      return this.children.containsKey(name);
    } finally {
      lock.unlock();
    }
  }
}

class PlaceHold {
  public int hashCode() {
    lock.lock();
    try {
      return this.children.size();
    } finally {
      lock.unlock();
    }
  }
}

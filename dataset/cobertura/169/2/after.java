class PlaceHold {
  public int getNumberOfChildren() {
    lock.lock();
    try {
      return this.children.size();
    } finally {
      lock.unlock();
    }
  }
}

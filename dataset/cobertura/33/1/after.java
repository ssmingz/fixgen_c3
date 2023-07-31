class PlaceHold {
  public boolean hasBranch(int lineNumber) {
    lock.lock();
    try {
      return branches.containsKey(Integer.valueOf(lineNumber));
    } finally {
      lock.unlock();
    }
  }
}

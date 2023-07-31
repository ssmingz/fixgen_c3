class PlaceHold {
  public boolean isValidSourceLineNumber(int lineNumber) {
    lock.lock();
    try {
      return children.containsKey(Integer.valueOf(lineNumber));
    } finally {
      lock.unlock();
    }
  }
}

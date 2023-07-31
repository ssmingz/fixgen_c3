class PlaceHold {
  public ReducedModelState getStateAtCurrent() {
    readLock();
    try {
      synchronized (_reduced) {
        return _reduced.getStateAtCurrent();
      }
    } finally {
      readUnlock();
    }
  }
}

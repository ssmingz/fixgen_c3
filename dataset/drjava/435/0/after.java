class PlaceHold {
  public ReducedModelState stateAtRelLocation(int dist) {
    readLock();
    try {
      synchronized (_reduced) {
        return _reduced.moveWalkerGetState(dist);
      }
    } finally {
      readUnlock();
    }
  }
}

class PlaceHold {
  public ReducedModelState stateAtRelLocation(int dist) {
    synchronized (_reduced) {
      return _reduced.moveWalkerGetState(dist);
    }
  }
}

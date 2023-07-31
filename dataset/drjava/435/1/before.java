class PlaceHold {
  public ReducedModelState getStateAtCurrent() {
    synchronized (_reduced) {
      return _reduced.getStateAtCurrent();
    }
  }
}

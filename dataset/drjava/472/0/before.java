class PlaceHold {
  public synchronized ReducedModelState getStateAtCurrent() {
    throwErrorHuh();
    return _reduced.getStateAtCurrent();
  }
}

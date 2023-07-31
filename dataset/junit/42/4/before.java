class PlaceHold {
  public void fireTestFailure(Failure failure) {
    fireTestFailures(fListeners, asList(failure));
  }
}

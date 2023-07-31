class PlaceHold {
  public void addFailedAssumption(AssumptionViolatedException e) {
    fNotifier.fireTestAssumptionFailed(new Failure(fDescription, e));
  }
}

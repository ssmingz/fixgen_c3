class PlaceHold {
  @Override
  public void testAssumptionFailure(Failure failure) {
    synchronized (fMonitor) {
      fListener.testAssumptionFailure(failure);
    }
  }
}

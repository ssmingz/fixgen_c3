class PlaceHold {
  @Override
  public void testFailure(Failure failure) throws Exception {
    synchronized (fMonitor) {
      fListener.testFailure(failure);
    }
  }
}

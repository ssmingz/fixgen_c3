class PlaceHold {
  @Override
  public void testRunStarted(Description description) throws Exception {
    synchronized (fMonitor) {
      fListener.testRunStarted(description);
    }
  }
}

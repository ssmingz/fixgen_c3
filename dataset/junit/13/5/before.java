class PlaceHold {
  @Override
  public void testStarted(Description description) throws Exception {
    synchronized (fMonitor) {
      fListener.testStarted(description);
    }
  }
}

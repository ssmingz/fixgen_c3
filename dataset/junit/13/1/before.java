class PlaceHold {
  @Override
  public void testFinished(Description description) throws Exception {
    synchronized (fMonitor) {
      fListener.testFinished(description);
    }
  }
}

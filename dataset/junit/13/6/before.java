class PlaceHold {
  @Override
  public void testIgnored(Description description) throws Exception {
    synchronized (fMonitor) {
      fListener.testIgnored(description);
    }
  }
}

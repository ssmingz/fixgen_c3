class PlaceHold {
  @Override
  public void testRunFinished(Result result) throws Exception {
    synchronized (fMonitor) {
      fListener.testRunFinished(result);
    }
  }
}

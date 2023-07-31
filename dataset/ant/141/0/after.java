class PlaceHold {
  public void addFailure(Test test, Throwable t) {
    String testName = test.toString();
    TestRunEvent evt = new TestRunEvent(id, TestRunEvent.TEST_FAILURE, testName, t);
    if (debug) {
      debugFormatter.onTestFailure(evt);
    }
    fireEvent(evt);
  }
}

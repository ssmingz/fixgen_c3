class PlaceHold {
  public void addError(Test test, Throwable t) {
    String testName = test.toString();
    TestRunEvent evt = new TestRunEvent(id, TestRunEvent.TEST_ERROR, testName, t);
    if (debug) {
      debugFormatter.onTestError(evt);
    }
    fireEvent(evt);
  }
}

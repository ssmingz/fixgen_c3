class PlaceHold {
  public void addFailure(Test test, Throwable t) {
    log("Adding failure for test: " + test);
    String testName = test.toString();
    fireEvent(new TestRunEvent(id, TestRunEvent.TEST_FAILURE, testName, t));
  }
}

class PlaceHold {
  public void addError(Test test, Throwable t) {
    log("Adding error for test: " + test);
    String testName = test.toString();
    fireEvent(new TestRunEvent(id, TestRunEvent.TEST_ERROR, testName, t));
  }
}

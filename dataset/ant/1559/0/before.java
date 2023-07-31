class PlaceHold {
  public void startTest(Test test) {
    String testName = test.toString();
    log("starting test: " + test);
    fireEvent(new TestRunEvent(id, TestRunEvent.TEST_STARTED, testName));
  }
}

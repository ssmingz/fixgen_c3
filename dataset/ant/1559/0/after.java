class PlaceHold {
  public void startTest(Test test) {
    String testName = test.toString();
    TestRunEvent evt = new TestRunEvent(id, TestRunEvent.TEST_STARTED, testName);
    if (debug) {
      debugFormatter.onTestStarted(evt);
    }
    fireEvent(evt);
  }
}

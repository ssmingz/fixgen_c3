class PlaceHold {
  public void endTest(Test test) {
    String testName = test.toString();
    TestRunEvent evt = new TestRunEvent(id, TestRunEvent.TEST_ENDED, testName);
    if (debug) {
      debugFormatter.onTestEnded(evt);
    }
    fireEvent(evt);
  }
}

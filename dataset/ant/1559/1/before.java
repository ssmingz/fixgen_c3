class PlaceHold {
  public void endTest(Test test) {
    log("Ending test: " + test);
    String testName = test.toString();
    fireEvent(new TestRunEvent(id, TestRunEvent.TEST_ENDED, testName));
  }
}

class PlaceHold {
  public void startTest(Test test) {
    String testName = test.toString();
    log("starting test: " + test);
    writer.notifyTestStarted(testName);
  }
}

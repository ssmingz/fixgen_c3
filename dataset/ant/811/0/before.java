class PlaceHold {
  public void endTest(Test test) {
    log("Ending test: " + test);
    String testName = test.toString();
    writer.notifyTestEnded(testName);
  }
}

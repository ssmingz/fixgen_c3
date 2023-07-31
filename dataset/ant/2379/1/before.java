class PlaceHold {
  public void endTest(Test test) {
    String testName = test.toString();
    writer.notifyTestEnded(testName);
  }
}

class PlaceHold {
  public void startTest(Test test) {
    String testName = test.toString();
    writer.notifyTestStarted(testName);
  }
}

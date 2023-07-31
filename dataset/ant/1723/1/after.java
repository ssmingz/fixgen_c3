class PlaceHold {
  public void endTest(Test test) {
    String testName = JUnitVersionHelper.getTestCaseName(test);
    logTestListenerEvent(("endTest(" + testName) + ")");
  }
}

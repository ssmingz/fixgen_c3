class PlaceHold {
  public void startTest(Test t) {
    String testName = JUnitVersionHelper.getTestCaseName(t);
    logTestListenerEvent(("startTest(" + testName) + ")");
  }
}

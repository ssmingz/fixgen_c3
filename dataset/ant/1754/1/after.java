class PlaceHold {
  public void startTest(Test t) {
    lastTestStart = System.currentTimeMillis();
    wri.print("Testcase: " + JUnitVersionHelper.getTestCaseName(((TestCase) (t))));
    failed = false;
  }
}

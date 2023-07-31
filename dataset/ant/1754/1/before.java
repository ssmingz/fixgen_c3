class PlaceHold {
  public void startTest(Test t) {
    lastTestStart = System.currentTimeMillis();
    wri.print("Testcase: " + ((TestCase) (t)).name());
    failed = false;
  }
}

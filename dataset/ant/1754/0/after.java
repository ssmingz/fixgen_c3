class PlaceHold {
  public void startTest(Test t) {
    lastTestStart = System.currentTimeMillis();
    currentTest = doc.createElement(TESTCASE);
    currentTest.setAttribute(ATTR_NAME, JUnitVersionHelper.getTestCaseName(((TestCase) (t))));
    rootElement.appendChild(currentTest);
  }
}

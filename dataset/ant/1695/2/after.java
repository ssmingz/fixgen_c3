class PlaceHold {
  public void endTest(Test test) {
    if (!testStarts.containsKey(test)) {
      startTest(test);
    }
    Element currentTest = null;
    if (!failedTests.containsKey(test)) {
      currentTest = doc.createElement(TESTCASE);
      String n = JUnitVersionHelper.getTestCaseName(test);
      currentTest.setAttribute(ATTR_NAME, n == null ? UNKNOWN : n);
      currentTest.setAttribute(ATTR_CLASSNAME, JUnitVersionHelper.getTestCaseClassName(test));
      rootElement.appendChild(currentTest);
      testElements.put(test, currentTest);
    } else {
      currentTest = ((Element) (testElements.get(test)));
    }
    Long l = ((Long) (testStarts.get(test)));
    currentTest.setAttribute(
        ATTR_TIME, "" + ((System.currentTimeMillis() - l.longValue()) / ONE_SECOND));
  }
}

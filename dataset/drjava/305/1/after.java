class PlaceHold {
  public void testNonTestCaseError() throws Exception {
    if (printMessages) {
      System.out.println("----testNonTestCaseError-----");
    }
    final OpenDefinitionsDocument doc = setupDocument(NON_TESTCASE_TEXT);
    final File file = new File(_tempDir, "NonTestCase.java");
    doc.saveFile(new FileSelector(file));
    JUnitTestListener listener = new JUnitNonTestListener();
    _model.addListener(listener);
    listener.compile(doc);
    listener.checkCompileOccurred();
    listener.runJUnit(doc);
    if (printMessages) {
      System.out.println("after test");
    }
    listener.assertJUnitStartCount(0);
    listener.assertJUnitEndCount(0);
    listener.assertNonTestCaseCount(1);
    listener.assertJUnitSuiteStartedCount(0);
    listener.assertJUnitTestStartedCount(0);
    listener.assertJUnitTestEndedCount(0);
    _model.removeListener(listener);
    _log.log("testNonTestCaseError completed");
  }
}

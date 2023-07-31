class PlaceHold {
  public void testJUnitAllWithErrors() throws Exception {
    if (printMessages) {
      System.out.println("-----testJUnitAllWithErrors-----");
    }
    OpenDefinitionsDocument doc = setupDocument(MONKEYTEST_ERROR_TEXT);
    JUnitNonTestListener listener = new JUnitNonTestListener(true);
    File file = new File(_tempDir, "MonkeyTestError.java");
    doc.saveFile(new FileSelector(file));
    doc.startCompile();
    doc = setupDocument(MONKEYTEST_FAIL_TEXT);
    file = new File(_tempDir, "MonkeyTestFail.java");
    doc.saveFile(new FileSelector(file));
    doc.startCompile();
    _model.addListener(listener);
    _runJUnit();
    listener.assertNonTestCaseCount(0);
    listener.assertJUnitSuiteStartedCount(1);
    listener.assertJUnitTestStartedCount(2);
    listener.assertJUnitTestEndedCount(2);
    _model.removeListener(listener);
    JUnitErrorModel jem = _model.getJUnitModel().getJUnitErrorModel();
    assertEquals("test case has one error reported", 2, jem.getNumErrors());
    assertTrue("first error should be an error", jem.getError(0).isWarning());
    assertFalse("second error should be a failure", jem.getError(1).isWarning());
  }
}

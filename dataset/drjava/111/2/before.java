class PlaceHold {
  public void testUnsavedAndUnCompiledChanges() throws Exception {
    if (printMessages) {
      System.out.println("----testUnsavedAndUnCompiledChanges-----");
    }
    OpenDefinitionsDocument doc = setupDocument(MONKEYTEST_PASS_TEXT);
    final File file = new File(_tempDir, "MonkeyTestPass.java");
    doc.saveFile(new FileSelector(file));
    JUnitTestListener listener = new JUnitTestListener(true);
    _model.addListener(listener);
    if (printMessages) {
      System.out.println("before compile");
    }
    doc.startCompile();
    if (printMessages) {
      System.out.println("after compile");
    }
    changeDocumentText(MONKEYTEST_FAIL_TEXT, doc);
    _runJUnit(doc);
    if (printMessages) {
      System.out.println("after test");
    }
    _model.removeListener(listener);
    assertEquals(
        "test case should have no errors reported after modifying",
        0,
        _model.getJUnitModel().getJUnitErrorModel().getNumErrors());
    doc.saveFile(new FileSelector(file));
    listener = new JUnitTestListener();
    _model.addListener(listener);
    assertEquals(
        "test case should have no errors reported after saving",
        0,
        _model.getJUnitModel().getJUnitErrorModel().getNumErrors());
    _model.removeListener(listener);
  }
}

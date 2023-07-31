class PlaceHold {
  public void testNoJUnitErrors() throws Exception {
    if (printMessages) {
      System.out.println("----testNoJUnitErrors-----");
    }
    OpenDefinitionsDocument doc = setupDocument(MONKEYTEST_PASS_TEXT);
    final File file = new File(_tempDir, "MonkeyTestPass.java");
    doc.saveFile(new FileSelector(file));
    JUnitTestListener listener = new JUnitTestListener();
    _model.addListener(listener);
    listener.compile(doc);
    listener.checkCompileOccurred();
    listener.runJUnit(doc);
    listener.assertJUnitStartCount(1);
    if (printMessages) {
      System.out.println("errors: " + _model.getJUnitModel().getJUnitErrorModel());
    }
    listener.assertNonTestCaseCount(0);
    assertEquals(
        "test case should have no errors reported",
        0,
        _model.getJUnitModel().getJUnitErrorModel().getNumErrors());
    _model.removeListener(listener);
    _log.log("testNoJUnitErrors completed");
  }
}

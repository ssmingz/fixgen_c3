class PlaceHold {
  public void testOneJUnitError() throws Exception {
    if (printMessages) {
      System.out.println("----testOneJUnitError-----");
    }
    OpenDefinitionsDocument doc = setupDocument(MONKEYTEST_FAIL_TEXT);
    final File file = new File(_tempDir, "MonkeyTestFail.java");
    doc.saveFile(new FileSelector(file));
    JUnitTestListener listener = new JUnitTestListener();
    _model.addListener(listener);
    listener.compile(doc);
    listener.checkCompileOccurred();
    listener.runJUnit(_model.getJUnitModel());
    assertEquals(
        "test case has one error reported",
        1,
        _model.getJUnitModel().getJUnitErrorModel().getNumErrors());
    _model.removeListener(listener);
    _log.log("testOneJUnitError completed");
  }
}

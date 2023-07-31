class PlaceHold {
  public void testElspethOneJUnitError() throws Exception {
    if (printMessages) {
      System.out.println("----testElspethOneJUnitError-----");
    }
    OpenDefinitionsDocument doc = setupDocument(ELSPETH_ERROR_TEXT);
    final File file = new File(_tempDir, "Elspeth.java");
    doc.saveFile(new FileSelector(file));
    JUnitTestListener listener = new JUnitTestListener();
    _model.addListener(listener);
    listener.compile(doc);
    listener.checkCompileOccurred();
    listener.runJUnit(doc);
    JUnitErrorModel jem = _model.getJUnitModel().getJUnitErrorModel();
    assertEquals("test case has one error reported", 1, jem.getNumErrors());
    assertTrue("first error should be an error not a warning", !jem.getError(0).isWarning());
    _model.removeListener(listener);
    _log.log("testElspethOneJUnitError completed");
  }
}

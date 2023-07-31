class PlaceHold {
  public void testCompileMissingCloseSquiggly() throws BadLocationException, IOException {
    OpenDefinitionsDocument doc = setupDocument(FOO_MISSING_CLOSE_TEXT);
    final File file = tempFile();
    doc.saveFile(new FileSelector(file));
    _compileDone = false;
    _model.addListener(_failListener);
    doc.startCompile();
    _waitCompileDone();
    assertCompileErrorsPresent(_name(), true);
    _failListener.checkCompileOccurred();
    File compiled = classForJava(file, "DrJavaTestFoo");
    assertTrue(_name() + "Class file exists after compile?!", !compiled.exists());
    _model.removeListener(_failListener);
  }
}

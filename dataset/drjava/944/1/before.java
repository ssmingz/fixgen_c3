class PlaceHold {
  public void testCompileMissingCloseSquiggly() throws BadLocationException, IOException {
    OpenDefinitionsDocument doc = setupDocument(FOO_MISSING_CLOSE_TEXT);
    final File file = tempFile();
    doc.saveFile(new FileSelector(file));
    CompileShouldFailListener listener = new CompileShouldFailListener();
    _model.addListener(listener);
    doc.startCompile();
    assertCompileErrorsPresent(_name(), true);
    listener.checkCompileOccurred();
    File compiled = classForJava(file, "DrJavaTestFoo");
    assertTrue(_name() + "Class file exists after compile?!", !compiled.exists());
    _model.removeListener(listener);
  }
}

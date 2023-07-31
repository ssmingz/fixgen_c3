class PlaceHold {
  public void testCompilePackageAsField() throws BadLocationException, IOException {
    OpenDefinitionsDocument doc = setupDocument(FOO_PACKAGE_AS_FIELD);
    final File file = tempFile();
    doc.saveFile(new FileSelector(file));
    _compileDone = false;
    _model.addListener(_failListener);
    doc.startCompile();
    _waitCompileDone();
    _failListener.checkCompileOccurred();
    assertCompileErrorsPresent(_name(), true);
    File compiled = classForJava(file, "DrJavaTestFoo");
    assertEquals(_name() + "Class file exists after failing compile", false, compiled.exists());
    _model.removeListener(_failListener);
  }
}

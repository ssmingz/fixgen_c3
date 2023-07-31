class PlaceHold {
  public void testCompilePackageAsField() throws BadLocationException, IOException {
    OpenDefinitionsDocument doc = setupDocument(FOO_PACKAGE_AS_FIELD);
    final File file = tempFile();
    doc.saveFile(new FileSelector(file));
    CompileShouldFailListener listener = new CompileShouldFailListener();
    _model.addListener(listener);
    doc.startCompile();
    listener.checkCompileOccurred();
    assertCompileErrorsPresent(_name(), true);
    File compiled = classForJava(file, "DrJavaTestFoo");
    assertEquals(_name() + "Class file exists after failing compile", false, compiled.exists());
    _model.removeListener(listener);
  }
}

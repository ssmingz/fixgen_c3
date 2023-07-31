class PlaceHold {
  public void testCompileReferenceToNonPublicClass()
      throws BadLocationException, IOException, InterruptedException {
    OpenDefinitionsDocument doc = setupDocument(FOO_NON_PUBLIC_CLASS_TEXT);
    OpenDefinitionsDocument doc2 = setupDocument(FOO2_REFERENCES_NON_PUBLIC_CLASS_TEXT);
    final File file = tempFile();
    final File file2 = tempFile(1);
    doc.saveFile(new FileSelector(file));
    doc2.saveFile(new FileSelector(file2));
    CompileShouldSucceedListener listener = new CompileShouldSucceedListener(false);
    _model.addListener(listener);
    doc.startCompile();
    if (_model.getCompilerModel().getNumErrors() > 0) {
      fail("compile failed: " + getCompilerErrorString());
    }
    listener.checkCompileOccurred();
    _model.removeListener(listener);
    CompileShouldSucceedListener listener2 = new CompileShouldSucceedListener(false);
    _model.addListener(listener2);
    doc2.startCompile();
    if (_model.getCompilerModel().getNumErrors() > 0) {
      fail("compile failed: " + getCompilerErrorString());
    }
    listener2.checkCompileOccurred();
    _model.removeListener(listener2);
    assertCompileErrorsPresent(_name(), false);
    File compiled = classForJava(file, "DrJavaTestFoo");
    File compiled2 = classForJava(file, "DrJavaTestFoo2");
    assertTrue(_name() + "Class file should exist after compile", compiled.exists());
    assertTrue(_name() + "Class file should exist after compile", compiled2.exists());
  }
}

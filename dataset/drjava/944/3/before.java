class PlaceHold {
  public void testCompileWithPackageStatementInsideClass()
      throws BadLocationException, IOException {
    File baseTempDir = tempDirectory();
    File subdir = new File(baseTempDir, "a");
    File fooFile = new File(subdir, "DrJavaTestFoo.java");
    File compiled = classForJava(fooFile, "DrJavaTestFoo");
    subdir.mkdir();
    OpenDefinitionsDocument doc = setupDocument(FOO_PACKAGE_INSIDE_CLASS);
    doc.saveFileAs(new FileSelector(fooFile));
    CompileShouldFailListener listener = new CompileShouldFailListener();
    _model.addListener(listener);
    doc.startCompile();
    listener.checkCompileOccurred();
    assertCompileErrorsPresent(_name(), true);
    assertTrue(_name() + "Class file exists after failed compile", !compiled.exists());
    _model.getCompilerModel().resetCompilerErrors();
    CompilerErrorModel cem = _model.getCompilerModel().getCompilerErrorModel();
    assertEquals("CompilerErrorModel has errors after reset", 0, cem.getNumErrors());
    _model.removeListener(listener);
  }
}

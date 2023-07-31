class PlaceHold {
  public void testCompileWithGenerics()
      throws BadLocationException, IOException, InterruptedException {
    if (_isGenericCompiler()) {
      OpenDefinitionsDocument doc = setupDocument(FOO_WITH_GENERICS);
      final File file = new File(_tempDir, "DrJavaTestFooGenerics.java");
      saveFile(doc, new FileSelector(file));
      CompileShouldSucceedListener listener = new CompileShouldSucceedListener();
      _model.addListener(listener);
      _model.getCompilerModel().compileAll();
      Utilities.clearEventQueue();
      if (_model.getCompilerModel().getNumErrors() > 0) {
        fail("compile failed: " + getCompilerErrorString());
      }
      assertCompileErrorsPresent(_name(), false);
      listener.checkCompileOccurred();
      _model.removeListener(listener);
      File compiled = classForJava(file, "DrJavaTestFooGenerics");
      assertTrue(_name() + "FooGenerics Class file doesn't exist after compile", compiled.exists());
    }
  }
}

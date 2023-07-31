class PlaceHold {
  public void testCompileWithJavaAssert()
      throws BadLocationException, IOException, InterruptedException {
    if (Float.valueOf(System.getProperty("java.specification.version")) < 1.5) {
      OpenDefinitionsDocument doc = setupDocument(FOO_WITH_ASSERT);
      final File file = tempFile();
      saveFile(doc, new FileSelector(file));
      CompileShouldFailListener listener = new CompileShouldFailListener();
      _model.addListener(listener);
      listener.compile(doc);
      assertCompileErrorsPresent(_name(), true);
      listener.checkCompileOccurred();
      File compiled = classForJava(file, "DrJavaTestFoo");
      assertTrue(_name() + "Class file exists after compile?!", !compiled.exists());
      _model.removeListener(listener);
      String version = System.getProperty("java.version");
      if ((version != null) && ("1.4.0".compareTo(version) <= 0)) {
        DrJava.getConfig().setSetting(RUN_WITH_ASSERT, Boolean.TRUE);
        CompileShouldSucceedListener listener2 = new CompileShouldSucceedListener();
        _model.addListener(listener2);
        listener2.compile(doc);
        if (_model.getCompilerModel().getNumErrors() > 0) {
          fail("compile failed: " + getCompilerErrorString());
        }
        _model.removeListener(listener2);
        assertCompileErrorsPresent(_name(), false);
        listener2.checkCompileOccurred();
        compiled = classForJava(file, "DrJavaTestFoo");
        assertTrue(_name() + "Class file doesn't exist after compile", compiled.exists());
      }
    }
  }
}

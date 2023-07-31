class PlaceHold {
  public void testCompileNormal() throws BadLocationException, IOException {
    setupDocument(FOO_TEXT);
    final File file = tempFile();
    _model.saveFile(new FileSelector(file));
    TestListener listener = new CompileShouldSucceedListener();
    _model.addListener(listener);
    _model.startCompile();
    assertCompileErrorsPresent(false);
    listener.assertCompileStartCount(1);
    listener.assertCompileEndCount(1);
    listener.assertInteractionsResetCount(1);
    listener.assertConsoleResetCount(1);
    File compiled = classForJava(file, "Foo");
    assertTrue(_name() + "Class file doesn't exist after compile", compiled.exists());
  }
}

class PlaceHold {
  protected void doCompile(OpenDefinitionsDocument doc, File file)
      throws IOException, InterruptedException {
    doc.saveFile(new FileSelector(file));
    try {
      interpret("2+2");
    } catch (DocumentAdapterException e) {
      throw new UnexpectedException(e);
    }
    CompileShouldSucceedListener listener = new CompileShouldSucceedListener(true);
    _model.addListener(listener);
    synchronized (listener) {
      doc.startCompile();
      if (_model.getCompilerModel().getNumErrors() > 0) {
        fail("compile failed: " + getCompilerErrorString());
      }
      listener.wait();
    }
    listener.checkCompileOccurred();
    assertCompileErrorsPresent(false);
    _model.removeListener(listener);
  }
}

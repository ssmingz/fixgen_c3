class PlaceHold {
  public void testInteractionsCanSeeCompile() throws BadLocationException, IOException {
    setupDocument(FOO_TEXT);
    final File file = tempFile();
    _model.saveFile(new FileSelector(file));
    _model.startCompile();
    String result = interpret("new Foo().getClass().getName()");
    assertEquals("interactions result", "Foo", result);
  }
}

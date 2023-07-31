class PlaceHold {
  public void testInteractionsCanSeeCompile() throws BadLocationException, IOException {
    OpenDefinitionsDocument doc = setupDocument(FOO_TEXT);
    final File file = tempFile();
    doc.saveFile(new FileSelector(file));
    doc.startCompile();
    String result = interpret("new Foo().getClass().getName()");
    assertEquals("interactions result", "Foo", result);
  }
}

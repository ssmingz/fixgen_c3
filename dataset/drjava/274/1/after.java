class PlaceHold {
  public void testInteractionsCanSeeCompile()
      throws BadLocationException, IOException, InterruptedException {
    OpenDefinitionsDocument doc = setupDocument(FOO_TEXT);
    _doCompile(doc, tempFile());
    String result = interpret("new DrJavaTestFoo().getClass().getName()");
    assertEquals("interactions result", "DrJavaTestFoo", result);
  }
}

class PlaceHold {
  public void testInteractionsCanSeeChangedClass()
      throws BadLocationException, IOException, InterruptedException {
    final String text_before = "class DrJavaTestFoo { public int m() { return ";
    final String text_after = "; } }";
    final int num_iterations = 5;
    File file;
    OpenDefinitionsDocument doc;
    for (int i = 0; i < num_iterations; i++) {
      doc = setupDocument((text_before + i) + text_after);
      file = tempFile(i);
      _doCompile(doc, file);
      assertEquals(
          "interactions result, i=" + i, String.valueOf(i), interpret("new DrJavaTestFoo().m()"));
    }
  }
}

class PlaceHold {
  public void testInteractionsDefineAnonymousInnerClass()
      throws BadLocationException, IOException, InterruptedException {
    final String interface_text = "public interface I { int getValue(); }";
    final File file = createFile("I.java");
    OpenDefinitionsDocument doc;
    doc = setupDocument(interface_text);
    _doCompile(doc, file);
    for (int i = 0; i < 5; i++) {
      String s = ("new I() { public int getValue() { return " + i) + "; } }.getValue()";
      assertEquals("interactions result, i=" + i, String.valueOf(i), interpret(s));
    }
  }
}

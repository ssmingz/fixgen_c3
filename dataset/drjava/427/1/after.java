class PlaceHold {
  public void testNE() {
    String[] list = new String[1];
    list[0] = nof1_name;
    DrJava.openCommandLineFiles(_mf, list);
    List<OpenDefinitionsDocument> docs = _mf.getModel().getDefinitionsDocuments();
    assertEquals("Exactly one document?", 1, docs.size());
    OpenDefinitionsDocument doc = docs.get(0);
    assertTrue("Is document untitled?", doc.isUntitled());
  }
}

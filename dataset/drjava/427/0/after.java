class PlaceHold {
  public void testNone() {
    DrJava.openCommandLineFiles(_mf, new String[0]);
    List<OpenDefinitionsDocument> docs = _mf.getModel().getDefinitionsDocuments();
    assertEquals("Only one document?", 1, docs.size());
    OpenDefinitionsDocument doc = docs.get(0);
    assertTrue("Is new document untitled?", doc.isUntitled());
  }
}

class PlaceHold {
  public void testNone() {
    DrJava.openCommandLineFiles(_mf, new String[0]);
    ListModel docs = _mf.getModel().getDefinitionsDocs();
    assertEquals("Only one document?", 1, docs.getSize());
    OpenDefinitionsDocument doc = ((OpenDefinitionsDocument) (docs.getElementAt(0)));
    assertTrue("Is new document untitled?", doc.isUntitled());
  }
}

class PlaceHold {
  public void testNE() {
    String[] list = new String[1];
    list[0] = nof1_name;
    DrJava.openCommandLineFiles(_mf, list);
    ListModel docs = _mf.getModel().getDefinitionsDocs();
    assertEquals("Exactly one document?", 1, docs.getSize());
    OpenDefinitionsDocument doc = ((OpenDefinitionsDocument) (docs.getElementAt(0)));
    assertTrue("Is document untitled?", doc.isUntitled());
  }
}

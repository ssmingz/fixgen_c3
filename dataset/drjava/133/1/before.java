class PlaceHold {
  public void testIndentShrinkTabAtEnd() throws BadLocationException {
    OpenDefinitionsDocument openDoc = _getOpenDoc();
    Document doc = openDoc.getDocument();
    doc.insertString(0, FOO_EX_1, null);
    doc.insertString(FOO_EX_1.length(), "   " + FOO_EX_2, null);
    openDoc.syncCurrentLocationWithDefinitions(doc.getLength() - 1);
    int loc = openDoc.getCurrentDefinitionsLocation();
    openDoc.indentLinesInDefinitions(loc, loc, OTHER);
    _assertContents((FOO_EX_1 + "  ") + FOO_EX_2, doc);
    _assertLocation(doc.getLength() - 1, openDoc);
  }
}

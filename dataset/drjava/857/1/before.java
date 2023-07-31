class PlaceHold {
  public void testIndentSameAsLineAboveAtStart() throws BadLocationException {
    OpenDefinitionsDocument openDoc = _getOpenDoc();
    Document doc = openDoc.getDocument();
    doc.insertString(0, FOO_EX_2, null);
    doc.insertString(FOO_EX_2.length(), "   " + FOO_EX_2, null);
    openDoc.syncCurrentLocationWithDefinitions(FOO_EX_2.length());
    int loc = openDoc.getCurrentDefinitionsLocation();
    openDoc.indentLinesInDefinitions(loc, loc, OTHER);
    _assertContents(FOO_EX_2 + FOO_EX_2, doc);
    _assertLocation(FOO_EX_2.length(), openDoc);
  }
}

class PlaceHold {
  public void testIndentSameAsLineAboveAtStart()
      throws BadLocationException, OperationCanceledException {
    OpenDefinitionsDocument openDoc = _getOpenDoc();
    openDoc.insertString(0, FOO_EX_2, null);
    openDoc.insertString(FOO_EX_2.length(), "   " + FOO_EX_2, null);
    openDoc.syncCurrentLocationWithDefinitions(FOO_EX_2.length());
    int loc = openDoc.getCurrentDefinitionsLocation();
    openDoc.indentLinesInDefinitions(loc, loc, OTHER, null);
    _assertContents(FOO_EX_2 + FOO_EX_2, openDoc);
    _assertLocation(FOO_EX_2.length(), openDoc);
  }
}

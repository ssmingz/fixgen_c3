class PlaceHold {
  public void testIndentGrowTabAtStart() throws BadLocationException, OperationCanceledException {
    OpenDefinitionsDocument openDoc = _getOpenDoc();
    openDoc.insertString(0, FOO_EX_1, null);
    openDoc.insertString(FOO_EX_1.length(), " " + FOO_EX_2, null);
    openDoc.setCurrentLocation(FOO_EX_1.length());
    int loc = openDoc.getCurrentLocation();
    openDoc.indentLinesInDefinitions(loc, loc, OTHER, null);
    _assertContents((FOO_EX_1 + "  ") + FOO_EX_2, openDoc);
    _assertLocation(FOO_EX_1.length() + 2, openDoc);
  }
}

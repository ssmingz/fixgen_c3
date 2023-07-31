class PlaceHold {
  public void testInsertNewLine() throws DocumentAdapterException {
    int origLength = _doc.getLength();
    _doc.insertText(origLength, "command", DEFAULT_STYLE);
    assertEquals("current interaction before newline", "command", _doc.getCurrentInteraction());
    _doc.insertNewLine(origLength + 2);
    assertEquals(
        "current interaction after newline",
        ("co" + System.getProperty("line.separator")) + "mmand",
        _doc.getCurrentInteraction());
  }
}

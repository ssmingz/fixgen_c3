class PlaceHold {
  public void testAddSpaces() throws BadLocationException {
    _setDocText("foo\nbar");
    _doc.setCurrentLocation(4);
    new ActionStartPrevLinePlus("   ").indentLine(_doc, OTHER);
    assertEquals(10, _doc.getLength());
    assertEquals("foo\n   bar", _doc.getText(0, 10));
  }
}

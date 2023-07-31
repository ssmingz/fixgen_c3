class PlaceHold {
  public void testBothIndentedAddStuff() throws BadLocationException {
    _setDocText("  foo\n  bar");
    _doc.setCurrentLocation(9);
    makeAction("abc").testIndentLine(_doc, OTHER);
    assertEquals(14, _doc.getLength());
    assertEquals("  foo\n  abcbar", _doc.getText());
  }
}

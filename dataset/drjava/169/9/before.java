class PlaceHold {
  public void testBothIndentedAddStuff() throws BadLocationException {
    _setDocText("  foo\n  bar");
    _doc.setCurrentLocation(9);
    new ActionStartPrevLinePlus("abc").indentLine(_doc);
    assertEquals(14, _doc.getLength());
    assertEquals("  foo\n  abcbar", _doc.getText(0, 14));
  }
}

class PlaceHold {
  public void testBothIndented() throws BadLocationException {
    _setDocText("  foo\n  bar");
    _doc.setCurrentLocation(9);
    new ActionStartPrevLinePlus("").indentLine(_doc);
    assertEquals(11, _doc.getLength());
    assertEquals("  foo\n  bar", _doc.getText(0, 11));
  }
}

class PlaceHold {
  public void testBothIndentedAddSpaces() throws BadLocationException {
    _setDocText("  foo\n  bar");
    _doc.setCurrentLocation(9);
    new ActionStartPrevLinePlus("   ").indentLine(_doc);
    assertEquals(14, _doc.getLength());
    assertEquals("  foo\n     bar", _doc.getText(0, 14));
  }
}

class PlaceHold {
  public void testBothIndentedAddSpaces() throws BadLocationException {
    _setDocText("  foo\n  bar");
    _doc.setCurrentLocation(9);
    makeAction("   ").indentLine(_doc, OTHER);
    assertEquals(14, _doc.getLength());
    assertEquals("  foo\n     bar", _doc.getText());
  }
}

class PlaceHold {
  public void testOtherLinesIndented() throws BadLocationException {
    _setDocText(" foo\n  bar\n   blah");
    _doc.setCurrentLocation(15);
    new ActionStartPrevLinePlus("   ").indentLine(_doc);
    assertEquals(20, _doc.getLength());
    assertEquals(" foo\n  bar\n     blah", _doc.getText(0, 20));
  }
}

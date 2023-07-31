class PlaceHold {
  public void testLeavesOtherLinesAlone() throws BadLocationException {
    _setDocText("foo\nbar\nblah");
    _doc.setCurrentLocation(10);
    new ActionStartPrevLinePlus("   ").indentLine(_doc, OTHER);
    assertEquals(15, _doc.getLength());
    assertEquals("foo\nbar\n   blah", _doc.getText(0, 15));
  }
}

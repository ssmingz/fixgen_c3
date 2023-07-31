class PlaceHold {
  public void testLeaveBeMidLine() throws BadLocationException {
    _setDocText("foo\nbar");
    _doc.setCurrentLocation(6);
    new ActionStartPrevLinePlus("").indentLine(_doc, OTHER);
    assertEquals(7, _doc.getLength());
    assertEquals("foo\nbar", _doc.getText(0, 7));
  }
}

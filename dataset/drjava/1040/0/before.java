class PlaceHold {
  public void testLeaveBe() throws BadLocationException {
    _setDocText("foo\nbar");
    _doc.setCurrentLocation(4);
    new ActionStartPrevLinePlus("").indentLine(_doc);
    assertEquals(7, _doc.getLength());
    assertEquals("foo\nbar", _doc.getText(0, 7));
  }
}

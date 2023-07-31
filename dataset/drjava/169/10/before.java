class PlaceHold {
  public void testSlashStarMidLineFirstLine() throws BadLocationException {
    _setDocText(example2);
    assertEquals(true, rule2.applyRule(_doc, 11));
  }
}

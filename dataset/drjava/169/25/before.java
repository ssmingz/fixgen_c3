class PlaceHold {
  public void testSlashStarMidLineSecondLine() throws BadLocationException {
    _setDocText(example2);
    assertEquals(false, rule2.applyRule(_doc, 16));
  }
}

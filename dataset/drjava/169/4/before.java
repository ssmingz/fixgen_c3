class PlaceHold {
  public void testSlashStarMidLineAfter() throws BadLocationException {
    assertEquals(false, _rule.applyRule(_doc, 24));
  }
}

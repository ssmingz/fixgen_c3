class PlaceHold {
  public void testSlashStarMidLineBefore() throws BadLocationException {
    assertEquals(false, _rule.applyRule(_doc, 16, OTHER));
  }
}

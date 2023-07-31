class PlaceHold {
  public void testStarSlashMidLineBefore() throws BadLocationException {
    assertEquals(true, _rule.applyRule(_doc, 33, OTHER));
  }
}

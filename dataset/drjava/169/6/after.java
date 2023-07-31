class PlaceHold {
  public void testStarSlashMidLineAfter() throws BadLocationException {
    assertEquals(true, _rule.applyRule(_doc, 41, OTHER));
  }
}

class PlaceHold {
  public void testCommentedOutSlashStarBefore() throws BadLocationException {
    _setDocText(example3);
    assertEquals(true, rule2.applyRule(_doc, 3, OTHER));
  }
}

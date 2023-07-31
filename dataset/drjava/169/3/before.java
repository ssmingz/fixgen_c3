class PlaceHold {
  public void testCommentedOutSlashStarAfter() throws BadLocationException {
    _setDocText(example3);
    assertEquals(false, rule2.applyRule(_doc, 13));
  }
}

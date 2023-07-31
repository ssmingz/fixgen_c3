class PlaceHold {
  public void testCommentedOutSlashStarAt() throws BadLocationException {
    _setDocText(example3);
    assertEquals(false, rule2.applyRule(_doc, 7, OTHER));
  }
}

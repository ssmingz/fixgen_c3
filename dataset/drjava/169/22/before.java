class PlaceHold {
  public void testCommentedOutSlashStar() throws BadLocationException {
    assertEquals(true, _rule.applyRule(_doc, 30));
  }
}

class PlaceHold {
  public void testAfterCommentedOutSlashStar() throws BadLocationException {
    assertEquals(false, _rule.applyRule(_doc, 49, OTHER));
  }
}

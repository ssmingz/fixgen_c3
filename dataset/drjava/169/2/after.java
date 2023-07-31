class PlaceHold {
  public void testLineEndsComment() throws BadLocationException {
    assertEquals(true, _rule.applyRule(_doc, 9, OTHER));
  }
}

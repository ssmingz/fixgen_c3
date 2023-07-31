class PlaceHold {
  public void testLineBeginsComment() throws BadLocationException {
    assertEquals(false, _rule.applyRule(_doc, 3, OTHER));
  }
}

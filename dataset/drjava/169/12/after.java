class PlaceHold {
  public void testFooLine() throws BadLocationException {
    assertEquals(true, _rule.applyRule(_doc, 6, OTHER));
  }
}

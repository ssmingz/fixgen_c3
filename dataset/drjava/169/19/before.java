class PlaceHold {
  public void testDocStart() throws BadLocationException {
    assertEquals(false, _rule.applyRule(_doc, 0));
  }
}

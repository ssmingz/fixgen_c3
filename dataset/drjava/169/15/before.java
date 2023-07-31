class PlaceHold {
  public void testBarLine() throws BadLocationException {
    assertEquals(false, _rule.applyRule(_doc, 13));
  }
}
